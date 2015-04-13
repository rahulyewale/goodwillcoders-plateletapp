package org.ngo.think.dm.web.managebeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.ngo.think.dm.common.Context.ContextInfo;
import org.ngo.think.dm.common.communication.dto.ServiceRequest;
import org.ngo.think.dm.common.communication.dto.ServiceResponse;
import org.ngo.think.dm.common.constant.CommonConstants;
import org.ngo.think.dm.common.dto.DonationCenterDTO;
import org.ngo.think.dm.common.dto.DonorAppointmentDTO;
import org.ngo.think.dm.common.dto.DonorDTO;
import org.ngo.think.dm.common.dto.GetDonationCenterResponseDTO;
import org.ngo.think.dm.common.dto.SearchDonorRequestDTO;
import org.ngo.think.dm.common.dto.SearchDonorResponseDTO;
import org.ngo.think.dm.common.util.JsonUtil;
import org.ngo.think.dm.web.client.RestSeviceInvoker;
import org.ngo.think.dm.web.constant.WebConstant;

@SuppressWarnings("serial")
@ManagedBean(name = "searchDonorMB")
@RequestScoped
public class SearchDonorMB implements Serializable
{
	private SearchDonorRequestDTO donorRequestDTO = new SearchDonorRequestDTO();
	private List<DonorDTO> searchDonorList = new ArrayList<DonorDTO>();
	private List<DonorDTO> selectedDonorList = new ArrayList<DonorDTO>();

	private String smsMsg;
	private String uniqueRequestId;
	private String confirmMsg;
	
	private String bloodGroup;
	
	private String theme ;
	
	@ManagedProperty(value="#{searchDonorResponseMB}")
	private SearchDonorResponseMB searchDonorResponseMB = new SearchDonorResponseMB();

	public SearchDonorMB()
	{
	}
	
	@PostConstruct
	public void init()
	{
		populateDonationCenters();
	}

	public void searchDonor()
	{

		if(null==donorRequestDTO.getRequestDate())
		{
			donorRequestDTO.setRequestDate(new Date());
		}
		
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
		
		
		smsMsg = responseDTO.getIntialSmsText();
		confirmMsg = responseDTO.getConfirmSmsText();
		uniqueRequestId = responseDTO.getUniqueRequestId();

		System.out.println("Search submitted");
	}

	public void R()
	{
		// sending the list

		DonorAppointmentDTO donorAppointment = getSelectedDonorList();
		donorAppointment.setStatus(CommonConstants.ApplicationConstant.SMS);
		System.out.println("sms " + donorAppointment.getDonors().size());

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
		
		searchDonorResponseMB.setSearchDonorList(new ArrayList<DonorDTO>());
		searchDonorResponseMB.setDonorRequestDTO(new SearchDonorRequestDTO());
		searchDonorResponseMB.setSearchDonorResponseDTO(new SearchDonorResponseDTO());

	}

	private DonorAppointmentDTO getSelectedDonorList()
	{
		DonorAppointmentDTO donorAppointment = new DonorAppointmentDTO();
		donorAppointment.setDonors(selectedDonorList);
		donorAppointment.setCenterId(searchDonorResponseMB.getDonorRequestDTO().getDonationCentre());
		donorAppointment.setConfirmSMS(searchDonorResponseMB.getSearchDonorResponseDTO().getConfirmSmsText());
		donorAppointment.setInitialSMS(searchDonorResponseMB.getSearchDonorResponseDTO().getIntialSmsText());
		donorAppointment.setRequestedDate(searchDonorResponseMB.getDonorRequestDTO().getRequestDate());
		donorAppointment.setRequestTxnId(searchDonorResponseMB.getSearchDonorResponseDTO().getUniqueRequestId());
		for (DonorDTO donorDTO : searchDonorResponseMB.getSearchDonorList())
		{
			if (donorDTO.isSelectedDonor())
			{
				selectedDonorList.add(donorDTO);
			}
		}
		donorAppointment.setDonors(selectedDonorList);

		return donorAppointment;
	}

	public void confirmedDonor()
	{
		DonorAppointmentDTO donorAppointment = getSelectedDonorList();
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
		System.out.println("phone " + donorAppointment.getDonors().size());
		searchDonorResponseMB.setSearchDonorList(new ArrayList<DonorDTO>());
		searchDonorResponseMB.setDonorRequestDTO(new SearchDonorRequestDTO());
		searchDonorResponseMB.setSearchDonorResponseDTO(new SearchDonorResponseDTO());

	}

	public List<DonorDTO> getSearchDonorList()
	{

		return searchDonorList;
	}

	public void setSearchDonorList(List<DonorDTO> searchDonorList)
	{
		this.searchDonorList = searchDonorList;
	}

	public SearchDonorRequestDTO getDonorRequestDTO()
	{
		return donorRequestDTO;
	}

	public void setDonorRequestDTO(SearchDonorRequestDTO donorRequestDTO)
	{
		this.donorRequestDTO = donorRequestDTO;
	}

	public String getSmsMsg()
	{
		return smsMsg;
	}

	public void setSmsMsg(String smsMsg)
	{
		this.smsMsg = smsMsg;
	}

	public String getConfirmMsg()
	{
		return confirmMsg;
	}

	public void setConfirmMsg(String confirmMsg)
	{
		this.confirmMsg = confirmMsg;
	}

	public String getUniqueRequestId()
	{
		return uniqueRequestId;
	}

	public void setUniqueRequestId(String uniqueRequestId)
	{
		this.uniqueRequestId = uniqueRequestId;
	}

	public String getTheme()
	{
		return theme;
	}

	public void setTheme(String theme)
	{
		this.theme = theme;
	}

	public SearchDonorResponseMB getSearchDonorResponseMB()
	{
		return searchDonorResponseMB;
	}

	public void setSearchDonorResponseMB(SearchDonorResponseMB searchDonorResponseMB)
	{
		this.searchDonorResponseMB = searchDonorResponseMB;
	}

	public String getBloodGroup()
	{
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup)
	{
		this.bloodGroup = bloodGroup;
	}
	
	private void populateDonationCenters()
	{
		if (!searchDonorResponseMB.isDonationCentersSet())
		{
			GetDonationCenterResponseDTO donationCenterResponseDTO = fetchDonationCenters();

			DonationCenterDTO[] centerDTOs = new DonationCenterDTO[donationCenterResponseDTO.getDonationCenterDTOList().size()];
			for (int i = 0; i < donationCenterResponseDTO.getDonationCenterDTOList().size(); i++)
			{
				centerDTOs[i] = donationCenterResponseDTO.getDonationCenterDTOList().get(i);
			}
			searchDonorResponseMB.setDonationCenterDTOList(centerDTOs);
			searchDonorResponseMB.setDonationCentersSet(true);
		}
	}
	
	private GetDonationCenterResponseDTO fetchDonationCenters()
	{
		ServiceRequest serviceRequest = new ServiceRequest(new ContextInfo());
		String serviceResponseString = null;
		ServiceResponse serviceResponse = null;
		GetDonationCenterResponseDTO donationCenterResponseDTO = null;
		try
		{
			serviceResponse = RestSeviceInvoker.invokeRestService(WebConstant.ServiceURL.GET_DONATION_CENTERS_URL, serviceRequest);

			serviceResponseString = JsonUtil.convertObjectToJson(serviceResponse.get(CommonConstants.ResponseKey.SEARCH_DOATION_CENTER_RESPONSE));
			donationCenterResponseDTO = (GetDonationCenterResponseDTO) JsonUtil.convertJsonToObject(serviceResponseString, GetDonationCenterResponseDTO.class);

		}
		catch (Exception e)
		{

			e.printStackTrace();
		}
		return donationCenterResponseDTO;
	}

}
