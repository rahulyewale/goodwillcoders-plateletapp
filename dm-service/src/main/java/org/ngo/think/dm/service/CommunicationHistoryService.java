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
		if(ApplicationConstant.SMS.equals(donorAppointmentDTO.getStatus()))
		{
			return sendSMSForSearchedDonors(donorAppointmentDTO);
			
		}else if(ApplicationConstant.CONFIRM_VIA_CALL.equals(donorAppointmentDTO.getStatus()))
		{
			return sendSMSForConfirmedDonors(donorAppointmentDTO);
		}
		return ResponseData.errorResponseData;
	}
	
	@Transactional
	public ResponseData sendSMSForSearchedDonors(DonorAppointmentDTO donorAppointmentDTO)
	{
		ResponseData responseData = ResponseData.successResponseData;
		try
		{
			List<String> donorsContacts = new ArrayList<String>();

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

					communicationHistory.setStatus("SMS_SENT");
					communicationHistory.setCreatedDate(new Date());
					communicationHistory.setCreatedBy("SYSTEM");

					communicationHistoryDAO.save(communicationHistory);

					donorsContacts.add(donorDTO.getDonorContactDetailsDTO().getContactNumber());
				}
				else
				{
					// STOP sms already sent
				}
			}

			String[] stringArray = donorsContacts.toArray(new String[donorsContacts.size()]);
			Way2Sms.sendSMS(stringArray, donorAppointmentDTO.getInitialSMS());
		}
		catch (Exception exception)
		{
			responseData = ResponseData.errorResponseData;
			responseData.setMessage(exception.getMessage());
		}
		return responseData;

	}
	
	@Transactional
	public ResponseData sendSMSForConfirmedDonors(DonorAppointmentDTO donorAppointmentDTO)
	{
		ResponseData responseData = ResponseData.successResponseData;
		try
		{
			List<String> donorsContacts = new ArrayList<String>();

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

					communicationHistory.setStatus("CONFIRMED");
					communicationHistory.setCreatedDate(new Date());
					communicationHistory.setCreatedBy("SYSTEM");

					communicationHistoryDAO.save(communicationHistory);

					donorsContacts.add(donorDTO.getDonorContactDetailsDTO().getContactNumber());
				}
				else
				{
					//if record present
					//assumption: only one record present
					CommunicationHistory communicationHistory = communicationHistories.get(0);
					if(HistoryStatus.CONFIRMED.equals(communicationHistory.getStatus()))
					{
						//STOP
					}
					else
					{
						communicationHistory.setStatus(HistoryStatus.CONFIRMED);
						communicationHistoryDAO.update(communicationHistory);
						
						donorsContacts.add(donorDTO.getDonorContactDetailsDTO().getContactNumber());
						//if extra contact available, add here.
					}
				}
			}

			String[] stringArray = donorsContacts.toArray(new String[donorsContacts.size()]);
			Way2Sms.sendSMS(stringArray, donorAppointmentDTO.getConfirmSMS());
		}
		catch (Exception exception)
		{
			responseData = ResponseData.errorResponseData;
			responseData.setMessage(exception.getMessage());
		}
		return responseData;

	}
		
}

