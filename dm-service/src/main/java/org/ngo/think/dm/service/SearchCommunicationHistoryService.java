package org.ngo.think.dm.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.ngo.think.dm.common.dto.DonationCenterDTO;
import org.ngo.think.dm.common.dto.SearchCommunicationHistoryRequestDTO;
import org.ngo.think.dm.common.dto.SearchCommunicationHistoryResponseDTO;
import org.ngo.think.dm.common.dto.SearchCommunicationHistoryResultDTO;
import org.ngo.think.dm.common.util.DateUtil;
import org.ngo.think.dm.persistence.dao.CommunicationHistoryDAO;
import org.ngo.think.dm.persistence.dao.DonationCenterDAO;
import org.ngo.think.dm.persistence.dao.DonorDAO;
import org.ngo.think.dm.persistence.dao.UniqueRequestDAO;
import org.ngo.think.dm.persistence.entity.CommunicationHistory;
import org.ngo.think.dm.persistence.entity.DonationCenter;
import org.ngo.think.dm.persistence.entity.Donor;
import org.ngo.think.dm.persistence.entity.UniqueRequestTxn;
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
		Date reqDate = null;
		List<CommunicationHistory> communicationHistories = communicationHistoryDAO.getCommunicationHistoryForScreen(searchCommunicationHistoryRequest);
		
		if(communicationHistories.size()>0)
		{
			reqDate = communicationHistories.get(0).getRequestedDate();
		}
		else
		{
			reqDate = searchCommunicationHistoryRequest.getDonationRequestDate();
		}
		
		UniqueRequestTxn uniqueRequestTxn = requestDAO.getUniqueRequestTxnByRequestID(searchCommunicationHistoryRequest.getRequestTxnId());
		DonationCenter center = null;
		try
		{
			center = centerDAO.findByPrimaryKey(uniqueRequestTxn.getDonationCenterId());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("In SearchCommunicationHistoryService : searchcommunicationhistory !!!");
		
		String centerDetails = center.getDonationCenterName() + "," + center.getCity() + "," + center.getPinCode();
		
		String confirmSMSWithCenter = DONATION_CENTRE.matcher(confirmSmsText).replaceAll(centerDetails);
		String confirmSMSWithReqDate = REQ_DATE.matcher(confirmSMSWithCenter).replaceAll(DateUtil.dateToString(reqDate));
		confirmSmsText = confirmSMSWithReqDate;

		SearchCommunicationHistoryResponseDTO communicationHistoryResponseDTO = new SearchCommunicationHistoryResponseDTO();
		
		ArrayList<SearchCommunicationHistoryResultDTO> communicationHistoryResultDTOs = new ArrayList<SearchCommunicationHistoryResultDTO>();
		for (CommunicationHistory communicationHistory : communicationHistories)
		{
			SearchCommunicationHistoryResultDTO communicationHistoryResultDTO = new SearchCommunicationHistoryResultDTO();
			
			communicationHistoryResultDTO.setCommunicationHistoryId(communicationHistory.getCommunicationHistoryId());
			
			DonationCenter donationCenter = communicationHistory.getDonationCenter();
			DonationCenterDTO donationCenterDTO = new DonationCenterDTO();
			donationCenterDTO.setAddressLine1(donationCenter.getAddressLine1());
			donationCenterDTO.setAddressLine2(donationCenter.getAddressLine2());
			donationCenterDTO.setDonationCenterName(donationCenter.getDonationCenterName());
			donationCenterDTO.setPinCode(donationCenter.getPinCode());
			donationCenterDTO.setState(donationCenter.getState());
			
			Donor donor = null;
			try
			{
				donor = donorDAO.findByPrimaryKey(communicationHistory.getDonorId());
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			communicationHistoryResultDTO.setDonationCenterDTO(donationCenterDTO);
			communicationHistoryResultDTO.setDonorName(donor.getFirstName()+" "+donor.getLastName());
			communicationHistoryResultDTO.setDonorUuid(donor.getDonorUuid());
			communicationHistoryResultDTO.setMobileNumber(donor.getDonorContactDetails().get(0).getContactNumber());
			communicationHistoryResultDTO.setRequestedDate(communicationHistory.getRequestedDate());
			communicationHistoryResultDTO.setSmsSentDate(communicationHistory.getSmsSentDate());
			communicationHistoryResultDTO.setStatus(communicationHistory.getStatus());
			
			communicationHistoryResultDTOs.add(communicationHistoryResultDTO);
			
		}
		
		communicationHistoryResponseDTO.setSearchCommunicationHistoryResponseList(communicationHistoryResultDTOs);
		communicationHistoryResponseDTO.setConfirmSMSText(confirmSmsText);

		return communicationHistoryResponseDTO;
	}

}
