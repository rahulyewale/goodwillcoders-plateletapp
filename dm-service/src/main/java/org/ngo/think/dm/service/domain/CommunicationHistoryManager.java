package org.ngo.think.dm.service.domain;

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
