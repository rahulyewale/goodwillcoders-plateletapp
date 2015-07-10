package org.ngo.think.dm.web.managebeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ngo.think.dm.common.Context.ContextInfo;
import org.ngo.think.dm.common.communication.dto.ServiceRequest;
import org.ngo.think.dm.common.communication.dto.ServiceResponse;
import org.ngo.think.dm.common.constant.CommonConstants;
import org.ngo.think.dm.common.dto.DonorAppointmentDTO;
import org.ngo.think.dm.common.dto.DonorDTO;
import org.ngo.think.dm.common.dto.SearchDonorRequestDTO;
import org.ngo.think.dm.common.dto.SearchDonorResponseDTO;
import org.ngo.think.dm.common.util.JsonUtil;
import org.ngo.think.dm.web.client.RestSeviceInvoker;
import org.ngo.think.dm.web.constant.WebConstant;

@SuppressWarnings("serial")
@ManagedBean(name = "searchDonorOnMapMB")
@ViewScoped
public class SearchDonorOnMapMB implements Serializable
{
	private SearchDonorRequestDTO donorRequestDTO = new SearchDonorRequestDTO();
	private List<DonorDTO> searchDonorList = new ArrayList<DonorDTO>();
	private List<DonorDTO> selectedDonorList = new ArrayList<DonorDTO>();
	private SearchDonorResponseDTO searchDonorResponseDTO = new SearchDonorResponseDTO();
	
	private String smsMsg;
	private String uniqueRequestId;
	private String confirmMsg;

	private String bloodGroup;

	private String theme;
	
	private String patientName;
	
	private String accordianActiveIndex;
	
	private String uniqueReqNumberText;


	@ManagedProperty(value = "#{cachedDataMB}")
	private CachedDataMB cachedDataMB = new CachedDataMB();
	
	@ManagedProperty(value = "#{dashbord}")
	private DashbordMB dashbordMB = new DashbordMB();


	@PostConstruct
	public void searchDonnorPostConstruct()
	{
		// view page only if logged in
		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = request.getSession();
		if (session.getAttribute("username") == null) {
			NavigationBean NB = new NavigationBean();
			NB.redirectToLogin();
		}
		System.out.println("session on map called");
		//

		if (null != dashbordMB.getSearchDonorRequestDTO())
		{
			setDonorRequestDTO(dashbordMB.getSearchDonorRequestDTO());
			//searchDonor();
			dashbordMB.setSearchDonorRequestDTO(null);
		}
		
		
		
		donorRequestDTO.setPlateletsBags(1);
	}
	
	public void searchDonor()
	{
		this.selectedDonorList = new ArrayList<DonorDTO>();
		setPatientName(donorRequestDTO.getPatientname());
		System.out.println("patient name = "+donorRequestDTO.getPatientname());

		//this.selectedDonorList = new ArrayList<DonorDTO>();

		if (null == donorRequestDTO.getRequestDate())
		{
			donorRequestDTO.setRequestDate(new Date());
		}

		// TODO set below 3 values from configuration.
		if (donorRequestDTO.getNotDonatedInLastMonthsCount() == null)
		{
			donorRequestDTO.setNotDonatedInLastMonthsCount(0);
		}
		// donorRequestDTO.setSearchAvailableDonorsOnly(false);
		if (donorRequestDTO.getNumberOfDonationsLessThanCount() == null)
		{
			donorRequestDTO.setNumberOfDonationsLessThanCount(24);
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

		smsMsg = responseDTO.getIntialSmsText();
		confirmMsg = responseDTO.getConfirmSmsText();
		uniqueRequestId = responseDTO.getUniqueRequestId();
		searchDonorResponseDTO.setDonationCenterDTO(responseDTO.getDonationCenterDTO());

		searchDonorResponseDTO.setIntialSmsText(smsMsg);
		searchDonorResponseDTO.setConfirmSmsText(confirmMsg);
		searchDonorResponseDTO.setUniqueRequestId(uniqueRequestId);
		
		System.out.println("Search submitted");
		
		
		///for showing contacted and confirm buttons
		int bagsno=donorRequestDTO.getPlateletsBags();
		if(bagsno>=1)
		{
			setAccordianActiveIndex("0");	
		}
		else
		{
			setAccordianActiveIndex("");
		}
		
		
		///for showing Unique Request Number
				int patientNameLength = patientName==null ? 0 : patientName.length();
				setUniqueReqNumberText(searchDonorResponseDTO.getUniqueRequestId());
				FacesMessage facesMessage;
				if(patientNameLength>=2)
					{
					facesMessage = new FacesMessage(uniqueReqNumberText);
					}
				else
					{
					facesMessage = new FacesMessage("Patient name should be present for generation of Request");
					}
				FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}

	public void sendSmsToSelectedDonors()
	{
		System.out.println("Send SMS to selected Donors....");
		manageDonorCommunication(CommonConstants.HistoryStatus.SMS_SENT);
		
		//FacesMessage facesMessage = new FacesMessage("Success : SMS sent.");
		//FacesContext.getCurrentInstance().addMessage("searchresult_frm_id:actionMessage", facesMessage);
	}

	public void confirmDonorAppointment()
	{
		System.out.println("Confirming Donor Appointment....");
		manageDonorCommunication(CommonConstants.HistoryStatus.CONFIRMED);
	}

	public void markDonorAsContacted()
	{
		System.out.println("Marking Donor as Contacted....");
		manageDonorCommunication(CommonConstants.HistoryStatus.CONTACTED);
	}

	private void manageDonorCommunication(String communicationStatus)
	{
		//this.selectedDonorList.clear();
		
		DonorAppointmentDTO donorAppointment = getSelectedDonorList();
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

		// searchDonorResponseMB.setSearchDonorList(new ArrayList<DonorDTO>());
		// searchDonorResponseMB.setDonorRequestDTO(new SearchDonorRequestDTO());
		// searchDonorResponseMB.setSearchDonorResponseDTO(new
		// SearchDonorResponseDTO());
	}

	private DonorAppointmentDTO getSelectedDonorList()
	{
		selectedDonorList.clear();
		DonorAppointmentDTO donorAppointment = new DonorAppointmentDTO();
		donorAppointment.setCenterId(this.getDonorRequestDTO().getDonationCentre());
		donorAppointment.setConfirmSMS(this.getSearchDonorResponseDTO().getConfirmSmsText());
		donorAppointment.setInitialSMS(this.getSearchDonorResponseDTO().getIntialSmsText());
		donorAppointment.setRequestedDate(this.getDonorRequestDTO().getRequestDate());
		donorAppointment.setRequestTxnId(this.getSearchDonorResponseDTO().getUniqueRequestId());
		
		for (DonorDTO donorDTO : this.getSearchDonorList())
		{
			if (donorDTO.isSelectedDonor())
			{
				selectedDonorList.add(donorDTO);
			}
		}
		donorAppointment.setDonors(selectedDonorList);
		return donorAppointment;
	}

	public SearchDonorResponseDTO getSearchDonorResponseDTO() 
	{
		return searchDonorResponseDTO;
	}

	public void setSearchDonorResponseDTO(
			SearchDonorResponseDTO searchDonorResponseDTO) 
	{
		this.searchDonorResponseDTO = searchDonorResponseDTO;
	}

	public CachedDataMB getCachedDataMB() {
		return cachedDataMB;
	}

	public void setCachedDataMB(CachedDataMB cachedDataMB) {
		this.cachedDataMB = cachedDataMB;
	}

	public void setSelectedDonorList(List<DonorDTO> selectedDonorList) 
	{
		this.selectedDonorList = selectedDonorList;
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

	/*public SearchDonorResponseMB getSearchDonorResponseMB()
	{
		return searchDonorResponseMB;
	}

	public void setSearchDonorResponseMB(SearchDonorResponseMB searchDonorResponseMB)
	{
		this.searchDonorResponseMB = searchDonorResponseMB;
	}*/

	public String getBloodGroup()
	{
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup)
	{
		this.bloodGroup = bloodGroup;
	}

	/*private void populateDonationCenters()
	{
		if (!searchDonorResponseMB.isDonationCentersSet())
		{
			GetDonationCenterResponseDTO donationCenterResponseDTO = fetchDonationCenters();

			if (null != donationCenterResponseDTO.getDonationCenterDTOList())
			{
				DonationCenterDTO[] centerDTOs = new DonationCenterDTO[donationCenterResponseDTO.getDonationCenterDTOList().size()];
				for (int i = 0; i < donationCenterResponseDTO.getDonationCenterDTOList().size(); i++)
				{
					centerDTOs[i] = donationCenterResponseDTO.getDonationCenterDTOList().get(i);
				}
				searchDonorResponseMB.setDonationCenterDTOList(centerDTOs);
				searchDonorResponseMB.setDonationCentersSet(true);
			}
		}
	}*/

	public List<DonorDTO> getSelectedDonors()
	{
		return selectedDonorList;
	}

	public void setSelectedDonors(List<DonorDTO> donorDTOList)
	{
		this.selectedDonorList = donorDTOList;
	}

	public DashbordMB getDashbordMB()
	{
		return dashbordMB;
	}

	public void setDashbordMB(DashbordMB dashbordMB)
	{
		this.dashbordMB = dashbordMB;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getAccordianActiveIndex() {
		return accordianActiveIndex;
	}

	public void setAccordianActiveIndex(String accordianActiveIndex) {
		this.accordianActiveIndex = accordianActiveIndex;
	}


	public String getUniqueReqNumberText() {
		return uniqueReqNumberText;
	}

	public void setUniqueReqNumberText(String uniqueReqNumberText) {
		this.uniqueReqNumberText = "Unique Request Number : "+uniqueReqNumberText;
	}
	
	
}
