package org.ngo.think.dm.service.mapper;

import org.ngo.think.dm.common.dto.DonorContactDetailsDTO;
import org.ngo.think.dm.persistence.entity.DonorContactDetail;
import org.ngo.think.dm.service.domain.Donor;

public class DonorContactMapper
{

	public static DonorContactDetailsDTO toDTO(DonorContactDetail donorContactDetail)
	{
		DonorContactDetailsDTO contactDetailsDTO = new DonorContactDetailsDTO();
		contactDetailsDTO.setContactNumber(donorContactDetail.getContactNumber());
		contactDetailsDTO.setContactNumberAlternate(donorContactDetail.getContactNumberAlternate());
		contactDetailsDTO.setEmail(donorContactDetail.getEmail());
		return contactDetailsDTO;
	}
	
	
	public static DonorContactDetail toEntity(Donor donor)
	{
		DonorContactDetail donorContactDetail = new DonorContactDetail();
		donorContactDetail.setContactNumber(donor.getContactNumber());
		donorContactDetail.setEmail(donor.getEmail());
		return donorContactDetail;
	}

}
