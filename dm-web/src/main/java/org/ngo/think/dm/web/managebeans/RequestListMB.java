package org.ngo.think.dm.web.managebeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.ngo.think.dm.common.Context.ContextInfo;
import org.ngo.think.dm.common.communication.dto.ServiceRequest;
import org.ngo.think.dm.common.communication.dto.ServiceResponse;
import org.ngo.think.dm.common.constant.CommonConstants;
import org.ngo.think.dm.common.dto.GetRequestListInputDTO;
import org.ngo.think.dm.common.dto.GetRequestListResponse;
import org.ngo.think.dm.common.dto.UniqueRequestDTO;
import org.ngo.think.dm.common.util.JsonUtil;
import org.ngo.think.dm.web.client.RestSeviceInvoker;
import org.ngo.think.dm.web.constant.WebConstant;

@ManagedBean(name = "requestListMB")
@SessionScoped
public class RequestListMB implements Serializable
{

	private static final long serialVersionUID = 1L;


	private GetRequestListInputDTO requestListInputDTO = new GetRequestListInputDTO();
	
	private List<UniqueRequestDTO> requestDTOList = new ArrayList<UniqueRequestDTO>();
	
	@ManagedProperty(value="#{requestDetailsMB}")
	private RequestDetailsMB requestDetailsMB = new RequestDetailsMB();
	
	@ManagedProperty(value = "#{searchDonorMB}")
	private SearchDonorMB searchDonorMB = new SearchDonorMB();

	
	public List<UniqueRequestDTO> getRequestDTOList()
	{
		return requestDTOList;
	}


	public void setRequestDTOList(List<UniqueRequestDTO> requestDTOList)
	{
		this.requestDTOList = requestDTOList;
	}
	
	public void openRequest(UniqueRequestDTO requestDTO)
	{
		requestDetailsMB.getCommunicationHistory(requestDTO);
		
		System.out.println(requestDTO.getRequestNumber());
	}


	public void searchRequestList(String calledFromPage)
	{
		ServiceRequest serviceRequest = new ServiceRequest(new ContextInfo());
		if (null != calledFromPage)
		{
			getRequestListInputDTO().setDonationCenterId(this.searchDonorMB.getDonorRequestDTO().getDonationCentre());
		}
		
		serviceRequest.add(CommonConstants.RequestKey.GET_REQUEST_LIST_REQUEST, getRequestListInputDTO());
		String serviceResponseString = null;
		ServiceResponse serviceResponse = null;
		GetRequestListResponse getRequestListResponse = null;
		try
		{
			serviceResponse = RestSeviceInvoker.invokeRestService(WebConstant.ServiceURL.GET_REQUEST_LIST_URL, serviceRequest);

			serviceResponseString = JsonUtil.convertObjectToJson(serviceResponse.get(CommonConstants.ResponseKey.GET_REQUEST_LIST_RESPONSE));
			getRequestListResponse = (GetRequestListResponse) JsonUtil.convertJsonToObject(serviceResponseString, GetRequestListResponse.class);

		}
		catch (Exception e)
		{

			e.printStackTrace();
		}

		setRequestDTOList(getRequestListResponse.getRequestListOutputList());
	}


	public GetRequestListInputDTO getRequestListInputDTO()
	{
		return requestListInputDTO;
	}


	public void setRequestListInputDTO(GetRequestListInputDTO getRequestListInputDTO)
	{
		this.requestListInputDTO = getRequestListInputDTO;
	}


	public RequestDetailsMB getRequestDetailsMB()
	{
		return requestDetailsMB;
	}


	public void setRequestDetailsMB(RequestDetailsMB requestDetailsMB)
	{
		this.requestDetailsMB = requestDetailsMB;
	}


	public SearchDonorMB getSearchDonorMB() {
		return searchDonorMB;
	}


	public void setSearchDonorMB(SearchDonorMB searchDonorMB) {
		this.searchDonorMB = searchDonorMB;
	}
	
	/*public void confirmCommunications()
	{
		
		ArrayList<SearchCommunicationHistoryResultDTO> filteredSearch = filteredSrchCommnHistResultList(this.srchCommnHistResultList);
		
		SearchCommunicationHistoryResponseDTO confirmSearchCommunicationHistoryResponseDTO  = new SearchCommunicationHistoryResponseDTO();
		confirmSearchCommunicationHistoryResponseDTO.setSearchCommunicationHistoryResponseList(filteredSearch);
		
		try
		{
			ServiceRequest serviceRequest = new ServiceRequest(new ContextInfo(), CommonConstants.RequestKey.CONFIRM_COMMUNICATION_HISTORY_REQUEST,confirmSearchCommunicationHistoryResponseDTO);
			ServiceResponse serviceResponse = null;
			
			serviceResponse =  RestSeviceInvoker.invokeRestService(WebConstant.ServiceURL.COMMUNICATION_HISTORY_SEARCH_SERVICE_URL, serviceRequest);
			String jsonResponseString = JsonUtil.convertObjectToJson(serviceResponse.get(CommonConstants.ResponseKey.CONFIRM_COMMUNICATION_HISTORY_RESPONSE));
			
			SearchCommunicationHistoryResponseDTO searchCommunicationHistoryResponse = (SearchCommunicationHistoryResponseDTO) JsonUtil.convertJsonToObject(jsonResponseString, SearchCommunicationHistoryResponseDTO.class);
			
			this.getSrchCommnHistResultList().clear();
			this.setSrchCommnHistResultList(searchCommunicationHistoryResponse.getSearchCommunicationHistoryResponseList());
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}*/
	
	/*private ArrayList<SearchCommunicationHistoryResultDTO> filteredSrchCommnHistResultList (List<SearchCommunicationHistoryResultDTO> allSrchCommnHistResultList)
	{
		ArrayList<SearchCommunicationHistoryResultDTO> filteredSrchCommnHistResultList = new ArrayList<SearchCommunicationHistoryResultDTO>();
		for(SearchCommunicationHistoryResultDTO srchHist : allSrchCommnHistResultList)
		{
			if(srchHist.isHistorySelected())
			{
				filteredSrchCommnHistResultList.add(srchHist);
			}
		}
		
		return filteredSrchCommnHistResultList;
	}
	*/
	/*public void cancelCommunications()
	{
		
	}*/
	
	
}
