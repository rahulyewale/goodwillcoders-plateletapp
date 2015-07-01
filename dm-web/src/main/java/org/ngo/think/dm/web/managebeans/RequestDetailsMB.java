package org.ngo.think.dm.web.managebeans;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.ngo.think.dm.common.Context.ContextInfo;
import org.ngo.think.dm.common.communication.dto.ResponseData;
import org.ngo.think.dm.common.communication.dto.ServiceRequest;
import org.ngo.think.dm.common.communication.dto.ServiceResponse;
import org.ngo.think.dm.common.constant.CommonConstants;
import org.ngo.think.dm.common.constant.CommonConstants.HistoryStatus;
import org.ngo.think.dm.common.constant.RequestStatus;
import org.ngo.think.dm.common.dto.DonationHistoryDTO;
import org.ngo.think.dm.common.dto.DonorAppointmentDTO;
import org.ngo.think.dm.common.dto.DonorContactDetailsDTO;
import org.ngo.think.dm.common.dto.DonorDTO;
import org.ngo.think.dm.common.dto.SearchCommunicationHistoryRequestDTO;
import org.ngo.think.dm.common.dto.SearchCommunicationHistoryResponseDTO;
import org.ngo.think.dm.common.dto.SearchCommunicationHistoryResultDTO;
import org.ngo.think.dm.common.dto.UniqueRequestDTO;
import org.ngo.think.dm.common.enums.ResponseType;
import org.ngo.think.dm.common.util.DateUtil;
import org.ngo.think.dm.common.util.JsonUtil;
import org.ngo.think.dm.web.client.RestSeviceInvoker;
import org.ngo.think.dm.web.constant.WebConstant;

@ManagedBean(name = "requestDetailsMB")
@ViewScoped
public class RequestDetailsMB implements Serializable
{

	private static final long serialVersionUID = 1L;

	private UniqueRequestDTO requestDTO = new UniqueRequestDTO();

	private SearchCommunicationHistoryRequestDTO searchCommunicationHistoryReqDTO = new SearchCommunicationHistoryRequestDTO();

	private List<SearchCommunicationHistoryResultDTO> communicationHistoryResult = new ArrayList<SearchCommunicationHistoryResultDTO>();
	
	private String donationRemarks;

	@ManagedProperty(value = "#{dashbord}")
	private DashbordMB dashbordMB = new DashbordMB();

	public DashbordMB getDashbordMB()
	{
		return dashbordMB;
	}

	public void setDashbordMB(DashbordMB dashbordMB)
	{
		this.dashbordMB = dashbordMB;
	}

	private String confirmSMSText;

	private SearchCommunicationHistoryResultDTO communicationHistoryResultDTO = new SearchCommunicationHistoryResultDTO();

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

	@PostConstruct
	public void openRequest()
	{
		setRequestDTO(dashbordMB.getSelectedRequestDTO());
		try
		{
			getRequestDTO().setDonationDate(DateUtil.stringToDate(getRequestDTO().getRequestedDate(),"dd-MMM-yyyy"));
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		getCommunicationHistory();

		System.out.println(requestDTO.getRequestNumber());
	}

	
	/////added
	public void getStatusCount(String reqno1)
	{
		String reqnoo=reqno1;
		setRequestDTO(getRequestDTO());
		getSearchCommunicationHistoryReqDTO().setRequestTxnId(reqnoo);

		try
		{
			ServiceRequest serviceRequest = new ServiceRequest(new ContextInfo(), CommonConstants.RequestKey.SEARCH_COMMUNICATION_HISTORY_REQUEST, getSearchCommunicationHistoryReqDTO());
			ServiceResponse serviceResponse = null;

			serviceResponse = RestSeviceInvoker.invokeRestService(WebConstant.ServiceURL.COMMUNICATION_HISTORY_SEARCH_SERVICE_URL, serviceRequest);
			String jsonResponseString = JsonUtil.convertObjectToJson(serviceResponse.get(CommonConstants.ResponseKey.SEARCH_COMMUNICATION_HISTORY_RESPONSE));

			SearchCommunicationHistoryResponseDTO searchCommunicationHistoryResponse = (SearchCommunicationHistoryResponseDTO) JsonUtil.convertJsonToObject(jsonResponseString, SearchCommunicationHistoryResponseDTO.class);

			this.communicationHistoryResult = searchCommunicationHistoryResponse.getSearchCommunicationHistoryResponseList();

			this.confirmSMSText = searchCommunicationHistoryResponse.getConfirmSMSText();

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		System.out.println(communicationHistoryResultDTO.getStatus());
		int occ = Collections.frequency(communicationHistoryResult, communicationHistoryResultDTO);
		System.out.println("occourences= "+occ);

	}
	
	//////
	
	
	
	
	
	
	public void getCommunicationHistory()
	{
		setRequestDTO(getRequestDTO());
		getSearchCommunicationHistoryReqDTO().setRequestTxnId(getRequestDTO().getRequestNumber());

		try
		{
			ServiceRequest serviceRequest = new ServiceRequest(new ContextInfo(), CommonConstants.RequestKey.SEARCH_COMMUNICATION_HISTORY_REQUEST, getSearchCommunicationHistoryReqDTO());
			ServiceResponse serviceResponse = null;

			serviceResponse = RestSeviceInvoker.invokeRestService(WebConstant.ServiceURL.COMMUNICATION_HISTORY_SEARCH_SERVICE_URL, serviceRequest);
			String jsonResponseString = JsonUtil.convertObjectToJson(serviceResponse.get(CommonConstants.ResponseKey.SEARCH_COMMUNICATION_HISTORY_RESPONSE));

			SearchCommunicationHistoryResponseDTO searchCommunicationHistoryResponse = (SearchCommunicationHistoryResponseDTO) JsonUtil.convertJsonToObject(jsonResponseString, SearchCommunicationHistoryResponseDTO.class);

			this.communicationHistoryResult = searchCommunicationHistoryResponse.getSearchCommunicationHistoryResponseList();

			this.confirmSMSText = searchCommunicationHistoryResponse.getConfirmSMSText();

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	public void confirmDonor()
	{

		maintainCommunicationStatus(communicationHistoryResultDTO, HistoryStatus.CONFIRMED);
	}

	public void markDonated()
	{
		maintainCommunicationStatus(communicationHistoryResultDTO, HistoryStatus.DONATED);
	}

	public void rejectDonor()
	{
		maintainCommunicationStatus(communicationHistoryResultDTO, HistoryStatus.REJECTED);
	}

	public void reserveDonor()
	{
		maintainCommunicationStatus(communicationHistoryResultDTO, HistoryStatus.RESERVED);
	}
	
	public void addDonationHistory()
	{
		DonationHistoryDTO donationHistoryDTO = new DonationHistoryDTO();
		donationHistoryDTO.setDonationDate(getRequestDTO().getDonationDate());
		donationHistoryDTO.setDonationComponentType("Platelets");
		donationHistoryDTO.setDonorId(getCommunicationHistoryResultDTO().getDonorId());
		donationHistoryDTO.setRemarks(getDonationRemarks());

		ServiceRequest serviceRequest = new ServiceRequest(new ContextInfo(), CommonConstants.RequestKey.ADD_DONATION_HISTORY, donationHistoryDTO);

		ServiceResponse serviceResponse = null;
		try
		{
			serviceResponse = RestSeviceInvoker.invokeRestService(WebConstant.ServiceURL.ADD_DONATION_HISTORY_SERVICE_URL, serviceRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		FacesMessage facesMessage = new FacesMessage("Successful", "Added communication history for");
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		
	}
	
	private void maintainCommunicationStatus(SearchCommunicationHistoryResultDTO communicationHistoryResultDTO, String communicationStatus)
	{
		FacesMessage facesMessage = null;

		System.out.println("Action on Donor : " + communicationHistoryResultDTO.getDonorName() + " "+communicationStatus);

		if (communicationHistoryResultDTO.getStatus().equals(communicationStatus))
		{
			facesMessage = new FacesMessage("Already "+communicationStatus, communicationHistoryResultDTO.getDonorName() + " is already "+communicationStatus);
		}
		else
		{

			DonorDTO donorDTO = new DonorDTO();
			DonorContactDetailsDTO donorContactDetailsDTO = new DonorContactDetailsDTO();
			donorContactDetailsDTO.setContactNumber(communicationHistoryResultDTO.getMobileNumber());

			donorDTO.setDonorContactDetailsDTO(donorContactDetailsDTO);
			donorDTO.setDonorId(communicationHistoryResultDTO.getDonorId());

			List<DonorDTO> selectedDonorList = new ArrayList<DonorDTO>();

			selectedDonorList.add(donorDTO);

			DonorAppointmentDTO donorAppointment = new DonorAppointmentDTO();
			donorAppointment.setDonors(selectedDonorList);
			donorAppointment.setCenterId(communicationHistoryResultDTO.getDonationCenterDTO().getDonationCenterId());
			donorAppointment.setConfirmSMS(this.confirmSMSText);
			try
			{
				donorAppointment.setRequestedDate(DateUtil.stringToDate(requestDTO.getRequestedDate(), "dd-MMM-yyyy"));
			}
			catch (ParseException e1)
			{
				e1.printStackTrace();
			}
			donorAppointment.setRequestTxnId(communicationHistoryResultDTO.getRequestId());
			donorAppointment.setStatus(communicationStatus);

			ServiceRequest serviceRequest = new ServiceRequest(new ContextInfo(), CommonConstants.RequestKey.SEND_SMS_REQUEST, donorAppointment);

			ServiceResponse serviceResponse = null;
			try
			{
				serviceResponse = RestSeviceInvoker.invokeRestService(WebConstant.ServiceURL.SEND_SMS_TO_DONORS_SERVICE_URL, serviceRequest);
			}
			catch (Exception e)
			{

				e.printStackTrace();
			}

			if (ResponseData.successResponseData.equals(serviceResponse.getResponseData()))
			{

				for (SearchCommunicationHistoryResultDTO communicationHistoryResultDTO2 : this.communicationHistoryResult)
				{
					if (communicationHistoryResultDTO2.getCommunicationHistoryId().equals(this.communicationHistoryResultDTO.getCommunicationHistoryId()))
					{
						communicationHistoryResultDTO2.setStatus(communicationStatus);
					}
				}
			}

			facesMessage = new FacesMessage("Successful", communicationHistoryResultDTO.getDonorName() + " " + communicationStatus.toLowerCase());
		}
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}

	public void closeRequest()
	{
		System.out.println("request details mb: close request");
		
		

		ServiceRequest serviceRequest = new ServiceRequest(new ContextInfo(), CommonConstants.RequestKey.UNIQUE_REQUEST_DTO, getRequestDTO());
		ServiceResponse serviceResponse = null;

		FacesMessage facesMessage = new FacesMessage("Successful", "Request closed");
		try
		{
			serviceResponse = RestSeviceInvoker.invokeRestService(WebConstant.ServiceURL.CLOSE_REQUEST_URL, serviceRequest);
			ResponseData responseData = serviceResponse.getResponseData();
			if (ResponseType.ERROR.equals(responseData.getResponseType()))
			{
				facesMessage = new FacesMessage("Error", responseData.getMessage());
			}
			else
			{
				getRequestDTO().setStatus(RequestStatus.CLOSED.toString());
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
	
	public void withdrawRequest()
	{
		System.out.println("request details mb: withdraw request");
		getRequestDTO().setStatus(RequestStatus.WITHDRAWN.toString());
		
		ServiceRequest serviceRequest = new ServiceRequest(new ContextInfo(), CommonConstants.RequestKey.UNIQUE_REQUEST_DTO, getRequestDTO());
		ServiceResponse serviceResponse = null;
		try
		{
			serviceResponse = RestSeviceInvoker.invokeRestService(WebConstant.ServiceURL.WITHDRAW_REQUEST_URL, serviceRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		FacesMessage facesMessage = new FacesMessage("Successful", "Request Withdrawn");
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
	
	public void setSelectedRow(SearchCommunicationHistoryResultDTO communicationHistoryResultDTO)
	{
		this.communicationHistoryResultDTO = communicationHistoryResultDTO;
	}


	public String getConfirmSMSText()
	{
		return confirmSMSText;
	}

	public void setConfirmSMSText(String confirmSMSText)
	{
		this.confirmSMSText = confirmSMSText;
	}

	public SearchCommunicationHistoryResultDTO getCommunicationHistoryResultDTO()
	{
		return communicationHistoryResultDTO;
	}

	public void setCommunicationHistoryResultDTO(SearchCommunicationHistoryResultDTO communicationHistoryResultDTO)
	{
		this.communicationHistoryResultDTO = communicationHistoryResultDTO;
	}

	public String getDonationRemarks()
	{
		return donationRemarks;
	}

	public void setDonationRemarks(String donationRemarks)
	{
		this.donationRemarks = donationRemarks;
	}


}
