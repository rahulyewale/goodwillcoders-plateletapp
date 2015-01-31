package org.ngo.think.dm.service.mapper;

import org.ngo.think.dm.common.dto.DonorAddressDetailsDTO;
import org.ngo.think.dm.persistence.entity.DonorAddressDetail;

public class DonorAddressMapper
{

	public static DonorAddressDetailsDTO toDTO(DonorAddressDetail donorAddressDetail)
	{
		DonorAddressDetailsDTO addressDetailsDTO = new DonorAddressDetailsDTO();
		
		addressDetailsDTO.setAddressLine1(donorAddressDetail.getAddressLine1());
		addressDetailsDTO.setAddressLine2(donorAddressDetail.getAddressLine2());
		addressDetailsDTO.setCity(donorAddressDetail.getCity());
		addressDetailsDTO.setDonorAddressId(donorAddressDetail.getDonorAddressId());
		addressDetailsDTO.setDonorId(donorAddressDetail.getDonor().getDonorId());
		addressDetailsDTO.setPinCode(donorAddressDetail.getPinCode());
		addressDetailsDTO.setState(donorAddressDetail.getState());
		return addressDetailsDTO;
	}

}
