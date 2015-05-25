package org.ngo.think.dm.service.domain;

import java.util.Date;

import org.ngo.think.dm.persistence.dao.CommunicationHistoryDAO;
import org.ngo.think.dm.persistence.entity.Donor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommunicationHistoryManager
{

	@Autowired
	private CommunicationHistoryDAO communicationHistoryDAO;
	
	public boolean isDonorAlreadyConfirmed(Donor donor, Date requirementDate)
	{
		//List<CommunicationHistory> communicationHistories = 
		return communicationHistoryDAO.isDonorConfirmedOnOrAfterRequirementDate(donor.getDonorId(), requirementDate);
		//return communicationHistories.size() > 0;
	}
}
