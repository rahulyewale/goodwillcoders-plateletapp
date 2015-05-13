package org.ngo.think.dm.service;

import static org.ngo.think.dm.common.constant.CommonConstants.HistoryStatus.CONFIRMED;
import static org.ngo.think.dm.common.constant.CommonConstants.HistoryStatus.CONTACTED;
import static org.ngo.think.dm.common.constant.CommonConstants.HistoryStatus.REJECTED;
import static org.ngo.think.dm.common.constant.CommonConstants.HistoryStatus.RESERVED;
import static org.ngo.think.dm.common.constant.CommonConstants.HistoryStatus.SMS_SENT;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.ngo.think.dm.common.communication.dto.ResponseData;
import org.ngo.think.dm.common.dto.DonorAppointmentDTO;
import org.ngo.think.dm.common.dto.DonorDTO;
import org.ngo.think.dm.common.enums.Feature;
import org.ngo.think.dm.persistence.dao.CommunicationHistoryDAO;
import org.ngo.think.dm.persistence.entity.CommunicationHistory;
import org.ngo.think.dm.persistence.entity.DonationCenter;
import org.ngo.think.dm.service.ff4j.FeatureProvider;
import org.ngo.think.dm.service.sms.util.Way2Sms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommunicationHistoryService
{

	@Autowired
	private CommunicationHistoryDAO communicationHistoryDAO;

	@Autowired
	private FeatureProvider featureProvider;

	@Transactional
	public ResponseData sendSMSToDonors(DonorAppointmentDTO donorAppointmentDTO)
	{
		List<String> contactList = new ArrayList<String>();
		boolean isStatusChanged = maintainCommunicationHistory(donorAppointmentDTO, donorAppointmentDTO.getStatus(), contactList);

		ResponseData responseData = ResponseData.successResponseData;
		if (isStatusChanged)
		{
			String appointmentStatus = donorAppointmentDTO.getStatus();
			if (SMS_SENT.equals(appointmentStatus) || CONFIRMED.equals(appointmentStatus))
			{
				featureProvider.getFF4j().getFeature(Feature.SEND_SMS_FEATURE.getFeatureName());
				boolean sendSMSEnabled = featureProvider.getFF4j().check(Feature.SEND_SMS_FEATURE.getFeatureName());
				if (sendSMSEnabled)
				{
					responseData = sendSMSToDonors(contactList, donorAppointmentDTO.getSMSText(donorAppointmentDTO.getStatus()));
				}
			}
		}
		else
		{
			responseData = ResponseData.errorResponseData;
		}
		return responseData;
	}

	public ResponseData sendSMSToDonors(List<String> donorsContacts, String smsText)
	{
		ResponseData responseData = ResponseData.successResponseData;
		try
		{

			if (!donorsContacts.isEmpty())
			{
				Way2Sms.initSMSSteps();

				String[] stringArray = donorsContacts.toArray(new String[donorsContacts.size()]);
				Way2Sms.sendSMS(stringArray, smsText);
			}

		}
		catch (Exception exception)
		{
			responseData = ResponseData.successResponseData;
			responseData.setMessage(exception.getMessage());
		}
		return responseData;

	}

	@Transactional
	public boolean maintainCommunicationHistory(DonorAppointmentDTO donorAppointmentDTO, String status, List<String> donorsContacts)
	{
		boolean isStatusChanged = true;

		ResponseData responseData = ResponseData.successResponseData;
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
					// New History (SMS SENT,CONFIRMED,CONTACTED)

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

					String statusFromDB = communicationHistory.getStatus();

					if (CONFIRMED.equals(status) && (SMS_SENT.equals(statusFromDB) || CONTACTED.equals(statusFromDB) || RESERVED.equals(statusFromDB) || REJECTED.equals(statusFromDB)))
					{
						communicationHistory.setStatus(CONFIRMED);
						communicationHistoryDAO.update(communicationHistory);

						donorsContacts.add(donorDTO.getDonorContactDetailsDTO().getContactNumber());
					}
					else if (CONTACTED.equals(status) && (statusFromDB.equals(SMS_SENT) || statusFromDB.equals(REJECTED)))
					{
						communicationHistory.setStatus(CONTACTED);
						communicationHistoryDAO.update(communicationHistory);

						donorsContacts.add(donorDTO.getDonorContactDetailsDTO().getContactNumber());
					}
					else if (REJECTED.equals(status) && (SMS_SENT.equals(statusFromDB) || CONTACTED.equals(statusFromDB) || RESERVED.equals(statusFromDB) || CONFIRMED.equals(statusFromDB)))
					{
						communicationHistory.setStatus(REJECTED);
						communicationHistoryDAO.update(communicationHistory);

						donorsContacts.add(donorDTO.getDonorContactDetailsDTO().getContactNumber());
					}
					else if (RESERVED.equals(status) && (SMS_SENT.equals(statusFromDB) || CONTACTED.equals(statusFromDB) || REJECTED.equals(statusFromDB) || CONFIRMED.equals(statusFromDB)))
					{
						communicationHistory.setStatus(RESERVED);
						communicationHistoryDAO.update(communicationHistory);

						donorsContacts.add(donorDTO.getDonorContactDetailsDTO().getContactNumber());
					}
					else
					{
						isStatusChanged = false;
					}

				}
			}

		}
		catch (Exception exception)
		{
			responseData = ResponseData.errorResponseData;
			responseData.setMessage(exception.getMessage());
		}
		return isStatusChanged;

	}

}
