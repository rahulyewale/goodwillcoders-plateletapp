package org.ngo.think.dm.service.domain;

import java.util.List;

import org.ngo.think.dm.persistence.entity.DonationHistory;
import org.springframework.stereotype.Component;

@Component
public class DonationHistoryManager
{

	
	public DonationInfo deriveLastDonationDateAndDonationCountForLast12Months(List<DonationHistory> donationHistory)
	{
		return new DonationInfo();
	}
}
