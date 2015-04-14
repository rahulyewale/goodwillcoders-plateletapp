package org.ngo.think.dm.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.ngo.think.dm.common.communication.dto.ResponseData;
import org.ngo.think.dm.common.constant.CommonConstants.ApplicationConstant;
import org.ngo.think.dm.common.constant.CommonConstants.HistoryStatus;
import org.ngo.think.dm.common.dto.DonorAppointmentDTO;
import org.ngo.think.dm.common.dto.DonorDTO;
import org.ngo.think.dm.persistence.dao.CommunicationHistoryDAO;
import org.ngo.think.dm.persistence.entity.CommunicationHistory;
import org.ngo.think.dm.persistence.entity.DonationCenter;
import org.ngo.think.dm.service.sms.util.Way2Sms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommunicationHistoryService
{

	@Autowired
	private CommunicationHistoryDAO communicationHistoryDAO;

	public ResponseData sendSMSToDonors(DonorAppointmentDTO donorAppointmentDTO)
	{
		if (ApplicationConstant.SMS.toString().equals(donorAppointmentDTO.getStatus()))
		{

			List<String> contanctList = maintainCommunicationHistory(donorAppointmentDTO, HistoryStatus.SMS_SENT);

			return sendSMSToDonors(contanctList, donorAppointmentDTO.getInitialSMS());

		}
		else if (ApplicationConstant.CONFIRM_VIA_CALL.toString().equals(donorAppointmentDTO.getStatus()))
		{
			List<String> contanctList = maintainCommunicationHistory(donorAppointmentDTO, HistoryStatus.CONFIRMED);
			return sendSMSToDonors(contanctList, donorAppointmentDTO.getConfirmSMS());
		}
		else if (ApplicationConstant.DONOR_CONTACTED.toString().equals(donorAppointmentDTO.getStatus()))
		{
			maintainCommunicationHistory(donorAppointmentDTO, HistoryStatus.CONTACTED);
			return ResponseData.successResponseData;
		}
		return ResponseData.errorResponseData;
	}
	
	@Transactional
	public ResponseData sendSMSToDonors(List<String> donorsContacts,String smsText)
	{
		ResponseData responseData = ResponseData.successResponseData;
		try
		{


			if(!donorsContacts.isEmpty())
			{
				Way2Sms.initSMSSteps();
				
				String[] stringArray = donorsContacts.toArray(new String[donorsContacts.size()]);
				Way2Sms.sendSMS(stringArray, smsText);
			}
			
		}
		catch (Exception exception)
		{
			responseData = ResponseData.errorResponseData;
			responseData.setMessage(exception.getMessage());
		}
		return responseData;

	}
	
	
	@Transactional
	public List<String> maintainCommunicationHistory(DonorAppointmentDTO donorAppointmentDTO,String status)
	{
		ResponseData responseData = ResponseData.successResponseData;
		List<String> donorsContacts = new ArrayList<String>();
		try
		{
			Long locationCenterId = donorAppointmentDTO.getCenterId();
			String uniqueRequestId = donorAppointmentDTO.getRequestTxnId();
			Date requestedDate = donorAppointmentDTO.getRequestedDate();

			DonationCenter donationCenter = new DonationCenter();
			donationCenter.setDonationCenterId(locationCenterId);

			List<DonorDTO> donors = donorAppointmentDTO.getDonors();
			for (Iterator<DonorDTO> iterator = donors.iterator(); iterator.hasNext();)
			{
				DonorDTO donorDTO = (DonorDTO) iterator.next();

				List<CommunicationHistory> communicationHistories = communicationHistoryDAO.getCommunicationHistoryForGivenCriteria(donorDTO.getDonorId(), donorAppointmentDTO);

				if (communicationHistories.isEmpty())
				{
					CommunicationHistory communicationHistory = new CommunicationHistory();
					communicationHistory.setRequestId(uniqueRequestId);
					communicationHistory.setDonorId(donorDTO.getDonorId());
					communicationHistory.setDonationCenter(donationCenter);
					communicationHistory.setRequestedDate(requestedDate);
					communicationHistory.setSmsSentDate(new Date());
					communicationHistory.setMobileNumber(donorDTO.getDonorContactDetailsDTO().getContactNumber());

					communicationHistory.setStatus(status);
					communicationHistory.setCreatedDate(new Date());
					communicationHistory.setCreatedBy("SYSTEM");

					communicationHistoryDAO.save(communicationHistory);

					donorsContacts.add(donorDTO.getDonorContactDetailsDTO().getContactNumber());
				}
				else
				{
					CommunicationHistory communicationHistory = communicationHistories.get(0);

					if (HistoryStatus.CONFIRMED.equals(status) && (communicationHistory.getStatus().equals(HistoryStatus.SMS_SENT) || communicationHistory.getStatus().equals(HistoryStatus.CONTACTED) || communicationHistory.getStatus().equals(HistoryStatus.RESERVED)))
					{
						communicationHistory.setStatus(HistoryStatus.CONFIRMED);
						communicationHistoryDAO.update(communicationHistory);

						donorsContacts.add(donorDTO.getDonorContactDetailsDTO().getContactNumber());
					}
					else if (HistoryStatus.CONTACTED.equals(status) && (communicationHistory.getStatus().equals(HistoryStatus.SMS_SENT)))
					{
						communicationHistory.setStatus(HistoryStatus.CONTACTED);
						communicationHistoryDAO.update(communicationHistory);

						donorsContacts.add(donorDTO.getDonorContactDetailsDTO().getContactNumber());
					}

				}
			}

			
		}
		catch (Exception exception)
		{
			responseData = ResponseData.errorResponseData;
			responseData.setMessage(exception.getMessage());
		}
		return donorsContacts;

	}
	
}

