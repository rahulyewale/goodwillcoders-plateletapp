package org.ngo.think.dm.service.mapper;

import java.util.Date;

import org.ngo.think.dm.common.dto.DonationHistoryDTO;
import org.ngo.think.dm.persistence.entity.DonationHistory;
import org.ngo.think.dm.persistence.entity.Donor;

public class DonationHistoryMapper
{

	public static DonationHistory toEntity(DonationHistoryDTO donationHistoryDTO)
	{
		DonationHistory donationHistory = new DonationHistory();
		
		donationHistory.setDonatedComponentType(donationHistoryDTO.getDonationComponentType());
		donationHistory.setDonationDate(donationHistoryDTO.getDonationDate());
		Donor donor = new Donor();
		donor.setDonorId(donationHistoryDTO.getDonorId());
		donationHistory.setDonor(donor);
		donationHistory.setIsrejected(false);
		donationHistory.setRemarks(donationHistoryDTO.getRemarks());
		donationHistory.setCreatedBy("System");
		donationHistory.setCreatedDate(new Date());
		
		return donationHistory;
		
	}
	
	

}
