package org.ngo.think.dm.service.mapper;

import org.ngo.think.dm.common.dto.DonorContactDetailsDTO;
import org.ngo.think.dm.persistence.entity.DonorContactDetail;

public class DonorContactMapper
{

	public DonorContactDetailsDTO toDTO(DonorContactDetail donorContactDetail)
	{
		DonorContactDetailsDTO contactDetailsDTO = new DonorContactDetailsDTO();
		contactDetailsDTO.setContactNumber(donorContactDetail.getContactNumber());
		contactDetailsDTO.setContactNumberAlternate(donorContactDetail.getContactNumberAlternate());
		return contactDetailsDTO;
	}

}
