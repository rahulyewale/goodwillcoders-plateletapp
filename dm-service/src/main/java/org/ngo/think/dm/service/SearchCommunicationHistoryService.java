package org.ngo.think.dm.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.ngo.think.dm.common.dto.DonationCenterDTO;
import org.ngo.think.dm.common.dto.SearchCommunicationHistoryRequestDTO;
import org.ngo.think.dm.common.dto.SearchCommunicationHistoryResponseDTO;
import org.ngo.think.dm.common.dto.SearchCommunicationHistoryResultDTO;
import org.ngo.think.dm.common.enums.Feature;
import org.ngo.think.dm.common.util.DateUtil;
import org.ngo.think.dm.persistence.dao.CommunicationHistoryDAO;
import org.ngo.think.dm.persistence.dao.DonationCenterDAO;
import org.ngo.think.dm.persistence.dao.DonorDAO;
import org.ngo.think.dm.persistence.dao.UniqueRequestDAO;
import org.ngo.think.dm.persistence.entity.CommunicationHistory;
import org.ngo.think.dm.persistence.entity.DonationCenter;
import org.ngo.think.dm.persistence.entity.Donor;
import org.ngo.think.dm.service.ff4j.FeatureProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class SearchCommunicationHistoryService
{
	
	@Autowired
	CommunicationHistoryDAO communicationHistoryDAO;
	
	@Value("${confirm.sms.text}")
	private String confirmSmsText;
	
	@Value("${confirm.appointment.text}")
	private String confirmAppointmentText;
	
	@Autowired
	private FeatureProvider featureProvider;
	
	@Autowired
	DonationCenterDAO centerDAO;
	
	@Autowired
	UniqueRequestDAO requestDAO;
	
	@Autowired
	DonorDAO donorDAO;
	
	private static final Pattern DONATION_CENTRE = Pattern.compile("#DONATION_CENTRE#");
	private static final Pattern REQ_UID = Pattern.compile("#REQ_UID#");
	private static final Pattern REQ_DATE = Pattern.compile("#REQ_DATE#");

	
	
	@Transactional
	public SearchCommunicationHistoryResponseDTO searchcommunicationhistory(SearchCommunicationHistoryRequestDTO searchCommunicationHistoryRequest)
	{
		String confirmSms = confirmAppointmentText;
		
		if(featureProvider.getFF4j().check(Feature.SEND_SMS_FEATURE.getFeatureName()))
		{
			confirmSms = confirmSmsText;
		}
		
		Date reqDate = null;
		String centerDetails = null;
		List<CommunicationHistory> communicationHistories = communicationHistoryDAO.getCommunicationHistoryForScreen(searchCommunicationHistoryRequest);
		
		if(communicationHistories.size()>0)
		{
			CommunicationHistory communicationHistory = communicationHistories.get(0);

			reqDate = communicationHistory.getRequestedDate();
			centerDetails = communicationHistory.getDonationCenter().getDonationCenterName() + "," + communicationHistory.getDonationCenter().getCity() + "," + communicationHistory.getDonationCenter().getPinCode();
			String confirmSMSWithCenter = DONATION_CENTRE.matcher(confirmSms).replaceAll(centerDetails);
			String confirmSMSWithReqDate = REQ_DATE.matcher(confirmSMSWithCenter).replaceAll(DateUtil.dateToString(reqDate));
			confirmSmsText = confirmSMSWithReqDate;
		}
		else
		{
			reqDate = searchCommunicationHistoryRequest.getDonationRequestDate();
		}
		
		System.out.println("In SearchCommunicationHistoryService : searchcommunicationhistory !!!");
		
		
		
		

		SearchCommunicationHistoryResponseDTO communicationHistoryResponseDTO = new SearchCommunicationHistoryResponseDTO();
		
		ArrayList<SearchCommunicationHistoryResultDTO> communicationHistoryResultDTOs = new ArrayList<SearchCommunicationHistoryResultDTO>();
		for (CommunicationHistory communicationHistory : communicationHistories)
		{
			SearchCommunicationHistoryResultDTO communicationHistoryResultDTO = new SearchCommunicationHistoryResultDTO();
			
			communicationHistoryResultDTO.setCommunicationHistoryId(communicationHistory.getCommunicationHistoryId());
			
			DonationCenter donationCenter = communicationHistory.getDonationCenter();
			DonationCenterDTO donationCenterDTO = new DonationCenterDTO();
			donationCenterDTO.setAddressLine1(donationCenter.getAddressLine1());
			//donationCenterDTO.setAddressLine2(donationCenter.getAddressLine2());
			donationCenterDTO.setDonationCenterName(donationCenter.getDonationCenterName());
			donationCenterDTO.setPinCode(donationCenter.getPinCode());
			donationCenterDTO.setState(donationCenter.getState());
			donationCenterDTO.setCity(donationCenter.getCity());
			donationCenterDTO.setDonationCenterId(donationCenter.getDonationCenterId());
			
			Donor donor = null;
			try
			{
				donor = donorDAO.findByPrimaryKey(communicationHistory.getDonorId());
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
			communicationHistoryResultDTO.setDonationCenterDTO(donationCenterDTO);
			communicationHistoryResultDTO.setDonorName(donor.getFirstName());
			communicationHistoryResultDTO.setDonorUuid(donor.getDonorUuid());
			communicationHistoryResultDTO.setMobileNumber(donor.getDonorContactDetails().get(0).getContactNumber());
			communicationHistoryResultDTO.setRequestedDate(communicationHistory.getRequestedDate());
			communicationHistoryResultDTO.setDonorId(donor.getDonorId());
			
			communicationHistoryResultDTO.setSmsSentDate(DateUtil.dateToString(communicationHistory.getSmsSentDate()));
			
			communicationHistoryResultDTO.setStatus(communicationHistory.getStatus());
			communicationHistoryResultDTO.setRequestId(communicationHistory.getRequestId());
			
			communicationHistoryResultDTOs.add(communicationHistoryResultDTO);
			
		}
		
		communicationHistoryResponseDTO.setSearchCommunicationHistoryResponseList(communicationHistoryResultDTOs);
		communicationHistoryResponseDTO.setConfirmSMSText(confirmSmsText);

		return communicationHistoryResponseDTO;
	}

}
