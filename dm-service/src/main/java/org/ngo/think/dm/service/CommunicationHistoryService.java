package org.ngo.think.dm.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.ngo.think.dm.common.communication.dto.ResponseData;
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
}
