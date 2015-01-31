package org.ngo.think.dm.service;

import org.ngo.think.dm.common.dto.DonorAppointmentDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommunicationHistoryService
{

	@Transactional
	public void sendSMSForSearchedDonors(DonorAppointmentDTO donorAppointmentDTO)
	{
		
	}
}
