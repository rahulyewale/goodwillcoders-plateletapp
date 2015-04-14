package org.ngo.think.dm.web.managebeans;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.ngo.think.dm.common.Context.ContextInfo;
import org.ngo.think.dm.common.communication.dto.ServiceRequest;
import org.ngo.think.dm.common.communication.dto.ServiceResponse;
import org.ngo.think.dm.common.constant.CommonConstants;
import org.ngo.think.dm.common.dto.DonorDTO;
import org.ngo.think.dm.common.dto.SearchCommunicationHistoryRequestDTO;
import org.ngo.think.dm.common.dto.SearchCommunicationHistoryResponseDTO;
import org.ngo.think.dm.common.dto.SearchCommunicationHistoryResultDTO;
import org.ngo.think.dm.common.dto.SearchDonorRequestDTO;
import org.ngo.think.dm.common.dto.SearchDonorResponseDTO;
import org.ngo.think.dm.common.dto.UniqueRequestDTO;
import org.ngo.think.dm.common.util.DateUtil;
import org.ngo.think.dm.common.util.JsonUtil;
import org.ngo.think.dm.web.client.RestSeviceInvoker;
import org.ngo.think.dm.web.constant.WebConstant;

@ManagedBean(name = "requestDetailsMB")
@SessionScoped
public class RequestDetailsMB implements Serializable
{

	private static final long serialVersionUID = 1L;

	private UniqueRequestDTO requestDTO = new UniqueRequestDTO();

	private SearchCommunicationHistoryRequestDTO searchCommunicationHistoryReqDTO = new SearchCommunicationHistoryRequestDTO();

	private List<SearchCommunicationHistoryResultDTO> communicationHistoryResult = new ArrayList<SearchCommunicationHistoryResultDTO>();
	
	private List<DonorDTO> searchDonorList = new ArrayList<DonorDTO>();
	
	@ManagedProperty(value="#{searchDonorResponseMB}")
	private SearchDonorResponseMB searchDonorResponseMB = new SearchDonorResponseMB();
	
	@ManagedProperty(value="#{searchDonorMB}")
	private SearchDonorMB searchDonorMB = new SearchDonorMB();

	public UniqueRequestDTO getRequestDTO()
	{
		return requestDTO;
	}

	public void setRequestDTO(UniqueRequestDTO requestDTO)
	{
		this.requestDTO = requestDTO;
	}

	public SearchCommunicationHistoryRequestDTO getSearchCommunicationHistoryReqDTO()
	{
		return searchCommunicationHistoryReqDTO;
	}

	public void setSearchCommunicationHistoryReqDTO(SearchCommunicationHistoryRequestDTO searchCommunicationHistoryReqDTO)
	{
		this.searchCommunicationHistoryReqDTO = searchCommunicationHistoryReqDTO;
	}

	public List<SearchCommunicationHistoryResultDTO> getCommunicationHistoryResult()
	{
		return communicationHistoryResult;
	}

	public void setCommunicationHistoryResult(List<SearchCommunicationHistoryResultDTO> communicationHistoryResult)
	{
		this.communicationHistoryResult = communicationHistoryResult;
	}

	public void getCommunicationHistory(UniqueRequestDTO inputRequestDTO)
	{
		setRequestDTO(inputRequestDTO);
		getSearchCommunicationHistoryReqDTO().setRequestTxnId(getRequestDTO().getRequestNumber());
		
		
		try
		{
			ServiceRequest serviceRequest = new ServiceRequest(new ContextInfo(), CommonConstants.RequestKey.SEARCH_COMMUNICATION_HISTORY_REQUEST,getSearchCommunicationHistoryReqDTO());
			ServiceResponse serviceResponse = null;
			
			serviceResponse =  RestSeviceInvoker.invokeRestService(WebConstant.ServiceURL.COMMUNICATION_HISTORY_SEARCH_SERVICE_URL, serviceRequest);
			String jsonResponseString = JsonUtil.convertObjectToJson(serviceResponse.get(CommonConstants.ResponseKey.SEARCH_COMMUNICATION_HISTORY_RESPONSE));
			
			SearchCommunicationHistoryResponseDTO searchCommunicationHistoryResponse = (SearchCommunicationHistoryResponseDTO) JsonUtil.convertJsonToObject(jsonResponseString, SearchCommunicationHistoryResponseDTO.class);
			
			this.communicationHistoryResult = searchCommunicationHistoryResponse.getSearchCommunicationHistoryResponseList();
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		
	}
	
	public void searchDonor()
	{
		//set selected donor list to empty as searchDonorMB is session scoped.
		searchDonorMB.setSearchDonorList(new ArrayList<DonorDTO>());
		SearchDonorRequestDTO donorRequestDTO = new SearchDonorRequestDTO();
		
		try
		{
			donorRequestDTO.setRequestDate(DateUtil.stringToDate(requestDTO.getRequestedDate(),"dd-MMM-yyyy"));
		}
		catch (ParseException e1)
		{
			e1.printStackTrace();
		}
		
		donorRequestDTO.setBloodGroup(requestDTO.getBloodGroup());
		donorRequestDTO.setDonationCentre(requestDTO.getDonationCenterDTO().getDonationCenterId());
		donorRequestDTO.setNotDonatedInLastMonthsCount(0);
		donorRequestDTO.setNumberOfDonationsLessThanCount(24);
		
		searchDonorMB.getDonorRequestDTO().setBloodGroup(requestDTO.getBloodGroup());
		searchDonorMB.getDonorRequestDTO().setRequestDate(donorRequestDTO.getRequestDate());
		searchDonorMB.getDonorRequestDTO().setDonationCentre(requestDTO.getDonationCenterDTO().getDonationCenterId());
		
		ServiceRequest serviceRequest = new ServiceRequest(new ContextInfo(), CommonConstants.RequestKey.SEARCH_DONOR_REQUEST, donorRequestDTO);

		ServiceResponse serviceResponse = null;
		try
		{
			serviceResponse = RestSeviceInvoker.invokeRestService(WebConstant.ServiceURL.SEARCH_DONOR_SERVICE_URL, serviceRequest);
		}
		catch (Exception e)
		{

			e.printStackTrace();
		}

		SearchDonorResponseDTO responseDTO = null;

		String responseString;
		try
		{
			responseString = JsonUtil.convertObjectToJson(serviceResponse.get(CommonConstants.ResponseKey.SEARCH_DONOR_RESPONSE));
			responseDTO = (SearchDonorResponseDTO) JsonUtil.convertJsonToObject(responseString, SearchDonorResponseDTO.class);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		searchDonorList = responseDTO.getDonorDTOList();
		
		searchDonorResponseMB.setSearchDonorResponseDTO(responseDTO);
		searchDonorResponseMB.setSearchDonorList(searchDonorList);
		searchDonorResponseMB.setDonorRequestDTO(donorRequestDTO);

		System.out.println("Search submitted");
		
	}

	public SearchDonorResponseMB getSearchDonorResponseMB()
	{
		return searchDonorResponseMB;
	}

	public void setSearchDonorResponseMB(SearchDonorResponseMB searchDonorResponseMB)
	{
		this.searchDonorResponseMB = searchDonorResponseMB;
	}

	public SearchDonorMB getSearchDonorMB()
	{
		return searchDonorMB;
	}

	public void setSearchDonorMB(SearchDonorMB searchDonorMB)
	{
		this.searchDonorMB = searchDonorMB;
	}

}
