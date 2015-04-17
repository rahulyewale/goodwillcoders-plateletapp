package org.ngo.think.dm.web.managebeans;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.ngo.think.dm.common.Context.ContextInfo;
import org.ngo.think.dm.common.communication.dto.ServiceRequest;
import org.ngo.think.dm.common.communication.dto.ServiceResponse;
import org.ngo.think.dm.common.constant.CommonConstants;
import org.ngo.think.dm.common.constant.CommonConstants.HistoryStatus;
import org.ngo.think.dm.common.dto.DonorAppointmentDTO;
import org.ngo.think.dm.common.dto.DonorContactDetailsDTO;
import org.ngo.think.dm.common.dto.DonorDTO;
import org.ngo.think.dm.common.dto.SearchCommunicationHistoryRequestDTO;
import org.ngo.think.dm.common.dto.SearchCommunicationHistoryResponseDTO;
import org.ngo.think.dm.common.dto.SearchCommunicationHistoryResultDTO;
import org.ngo.think.dm.common.dto.UniqueRequestDTO;
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
		getCommunicationHistory();
		
		System.out.println(requestDTO.getRequestNumber());
	}
	
	
	public void getCommunicationHistory()
	{
		setRequestDTO(getRequestDTO());
		getSearchCommunicationHistoryReqDTO().setRequestTxnId(getRequestDTO().getRequestNumber());
		
		
		try
		{
			ServiceRequest serviceRequest = new ServiceRequest(new ContextInfo(), CommonConstants.RequestKey.SEARCH_COMMUNICATION_HISTORY_REQUEST,getSearchCommunicationHistoryReqDTO());
			ServiceResponse serviceResponse = null;
			
			serviceResponse =  RestSeviceInvoker.invokeRestService(WebConstant.ServiceURL.COMMUNICATION_HISTORY_SEARCH_SERVICE_URL, serviceRequest);
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

		FacesMessage facesMessage = null;

		System.out.println("Confirming Donor : " + communicationHistoryResultDTO.getDonorName());

		if (communicationHistoryResultDTO.getStatus().equals(HistoryStatus.CONFIRMED))
		{
			facesMessage = new FacesMessage("Already Confirmed", communicationHistoryResultDTO.getDonorName() + " is Already CONFIRMED.");
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
			donorAppointment.setStatus(CommonConstants.ApplicationConstant.CONFIRM_VIA_CALL);

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

			for (SearchCommunicationHistoryResultDTO communicationHistoryResultDTO : this.communicationHistoryResult)
			{
				if (communicationHistoryResultDTO.getCommunicationHistoryId().equals(this.communicationHistoryResultDTO.getCommunicationHistoryId()))
				{
					communicationHistoryResultDTO.setStatus(HistoryStatus.CONFIRMED);
				}
			}

			facesMessage = new FacesMessage("Succesful", communicationHistoryResultDTO.getDonorName() + " is CONFIRMED.");
		}
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);

	}
	
	
	
	public void setSelectedRow(SearchCommunicationHistoryResultDTO communicationHistoryResultDTO)
	{
		this.communicationHistoryResultDTO = communicationHistoryResultDTO;
	}
	

	public void rejectDonor(SearchCommunicationHistoryResultDTO communicationHistoryResultDTO)
	{
		System.out.println("Rejecting Donor : "+ communicationHistoryResultDTO.getDonorName());
		try
		{
			Thread.sleep(2000);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
	public void reserveDonor(SearchCommunicationHistoryResultDTO communicationHistoryResultDTO)
	{
		System.out.println("Rejerving Donor : "+ communicationHistoryResultDTO.getDonorName());
		try
		{
			Thread.sleep(2000);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
	public void addDonationHistory(SearchCommunicationHistoryResultDTO communicationHistoryResultDTO)
	{
		System.out.println("Adding Donation History For : "+ communicationHistoryResultDTO.getDonorName());
		try
		{
			Thread.sleep(2000);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
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


}
