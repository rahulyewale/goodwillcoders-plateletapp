package org.ngo.think.dm.service.location.dto;

import org.ngo.think.dm.persistence.entity.Donor;
import org.springframework.stereotype.Component;

@Component
public class CommunicationHistoryManager
{

	
	public boolean isDonorAlreadyConfirmed(Donor donor)
	{
		return true;
	}
}
