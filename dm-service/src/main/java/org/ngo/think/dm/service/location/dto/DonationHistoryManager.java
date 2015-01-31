package org.ngo.think.dm.service.location.dto;

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
