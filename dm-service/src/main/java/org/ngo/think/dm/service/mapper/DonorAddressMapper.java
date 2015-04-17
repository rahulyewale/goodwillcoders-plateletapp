package org.ngo.think.dm.service.mapper;

import java.util.Date;

import org.ngo.think.dm.common.dto.DonorAddressDetailsDTO;
import org.ngo.think.dm.persistence.entity.DonorAddressDetail;
import org.ngo.think.dm.service.domain.Donor;

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
		addressDetailsDTO.setLongitude(donorAddressDetail.getLongitude());
		addressDetailsDTO.setLattitude(donorAddressDetail.getLattitude());
		return addressDetailsDTO;
	}
	
	
	public static DonorAddressDetail toEntity(Donor donor)
	{
		DonorAddressDetail donorAddressDetail = new DonorAddressDetail();
		donorAddressDetail.setAddressLine1(donor.getResidentialAddress());
		donorAddressDetail.setCity(donor.getCity());
		donorAddressDetail.setPinCode(donor.getPincode());
		donorAddressDetail.setState(donor.getState());
		donorAddressDetail.setCreatedBy("System");
		donorAddressDetail.setCreatedDate(new Date());
		
		return donorAddressDetail;
	}

}
