package org.ngo.think.dm.web.managebeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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
@ManagedBean(name = "searchDonorMB")
@SessionScoped
public class SearchDonorMB implements Serializable {
	private SearchDonorRequestDTO donorRequestDTO = new SearchDonorRequestDTO();
	private List<DonorDTO> searchDonorList = new ArrayList<DonorDTO>();
	private List<DonorDTO> selectedDonorList = new ArrayList<DonorDTO>();
	
	private String smsMsg;
	private String uniqueRequestId;
	private String confirmMsg;

	public SearchDonorMB() {
		// TODO Auto-generated constructor stub
	}

	public void searchDonor() {
		DonorDTO donorDTO = new DonorDTO();
		donorDTO.setFirstName("Rajiv");
		donorDTO.setLastName("Vaidya");
		this.getSearchDonorList().clear();
		this.getSearchDonorList().add(donorDTO);
		/*
		 * ServiceRequest serviceRequest = new ServiceRequest(new ContextInfo(),
		 * CommonConstants.RequestKey.SEARCH_DONOR_REQUEST,donorRequestDTO);
		 * ServiceResponse serviceResponse = null; try { serviceResponse =
		 * RestSeviceInvoker
		 * .invokeRestService(WebConstant.ServiceURL.SEARCH_DONOR_SERVICE_URL,
		 * serviceRequest); } catch (Exception e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); }
		 * 
		 * SearchDonorResponseDTO responseDTO = null;
		 * 
		 * String responseString; try { responseString =
		 * JsonUtil.convertObjectToJson
		 * (serviceResponse.get(CommonConstants.ResponseKey
		 * .SEARCH_DONOR_RESPONSE)); responseDTO = (SearchDonorResponseDTO)
		 * JsonUtil.convertJsonToObject(responseString,
		 * SearchDonorResponseDTO.class); } catch (Exception e) {
		 * e.printStackTrace(); } this.getSearchDonorList().add(donorDTO); //
		 * setting the response searchDonorList = responseDTO.getDonorDTOList();
		 * smsMsg = responseDTO.getIntialSmsText(); confirmMsg =
		 * responseDTO.getConfirmSmsText(); uniqueRequestId =
		 * responseDTO.getUniqueRequestId();
		 */

		System.out.println("Search submitted");
	}

	public void sendSMS() {
		// sending the list
		selectedDonorList = getSelectedDonorList();
		
		DonorAppointmentDTO donorAppointment = new DonorAppointmentDTO();
		donorAppointment.setDonors(selectedDonorList);
		donorAppointment.setCenterId(donorRequestDTO.getDonationCentre());
		donorAppointment.setConfirmSMS(confirmMsg);
		donorAppointment.setInitialSMS(smsMsg);
		donorAppointment.setRequestedDate(donorRequestDTO.getRequestDate());
		donorAppointment.setRequestTxnId(uniqueRequestId);
		donorAppointment.setStatus(CommonConstants.ApplicationConstant.SMS);

	}

	private List<DonorDTO> getSelectedDonorList() {

		for (DonorDTO donorDTO : searchDonorList) {
			if (donorDTO.isSelectedDonor()) {
				selectedDonorList.add(donorDTO);
			}
		}
		return selectedDonorList;
	}

	public void confirmedDonor() {
		System.out.println("confirm donor");
	}

	public List<DonorDTO> getSearchDonorList() {

		return searchDonorList;
	}

	public void setSearchDonorList(List<DonorDTO> searchDonorList) {
		this.searchDonorList = searchDonorList;
	}

	public SearchDonorRequestDTO getDonorRequestDTO() {
		return donorRequestDTO;
	}

	public void setDonorRequestDTO(SearchDonorRequestDTO donorRequestDTO) {
		this.donorRequestDTO = donorRequestDTO;
	}

	public String getSmsMsg() {
		return smsMsg;
	}

	public void setSmsMsg(String smsMsg) {
		this.smsMsg = smsMsg;
	}

	public String getConfirmMsg() {
		return confirmMsg;
	}

	public void setConfirmMsg(String confirmMsg) {
		this.confirmMsg = confirmMsg;
	}

	public String getUniqueRequestId() {
		return uniqueRequestId;
	}

	public void setUniqueRequestId(String uniqueRequestId) {
		this.uniqueRequestId = uniqueRequestId;
	}

}
