package org.ngo.think.dm.service.domain;

import java.util.List;

import org.ngo.think.dm.common.dto.DonorAppointmentDTO;
import org.ngo.think.dm.persistence.dao.CommunicationHistoryDAO;
import org.ngo.think.dm.persistence.entity.CommunicationHistory;
import org.ngo.think.dm.persistence.entity.Donor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommunicationHistoryManager
{

	@Autowired
	CommunicationHistoryDAO communicationHistoryDAO;
	
	public boolean isDonorAlreadyConfirmed(Donor donor, DonorAppointmentDTO appointmentDTO)
	{
		List<CommunicationHistory> communicationHistories = communicationHistoryDAO.getCommunicationHistoryForGivenCriteria(donor.getDonorId(), appointmentDTO);
		return communicationHistories.size() > 0;
	}
}
