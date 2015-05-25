package org.ngo.think.dm.service.mapper;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.ngo.think.dm.common.dto.DonorDTO;
import org.ngo.think.dm.common.util.DateUtil;
import org.ngo.think.dm.persistence.entity.Donor;
import org.ngo.think.dm.persistence.entity.DonorAddressDetail;
import org.ngo.think.dm.persistence.entity.DonorContactDetail;

public class DonorMapper
{

	public static DonorDTO toDTO(Donor donor)
	{
		DonorDTO donorDTO = new DonorDTO();
		donorDTO.setBirthDate(donor.getBirthDate());
		donorDTO.setBloodGroup(donor.getBloodGroup());
		donorDTO.setDistanceKm(donor.getDistanceInKm());
		donorDTO.setDonationComponentType(donor.getDonationComponentType());
		
		if(null!=donor.getDonorAddressDetails()&&donor.getDonorAddressDetails().size()!=0)
		{
			donorDTO.setDonorAddressDetailsDTO(DonorAddressMapper.toDTO(donor.getDonorAddressDetails().get(0)));	
		}
		if (null != donor.getDonorContactDetails() && donor.getDonorContactDetails().size() != 0)
		{
			donorDTO.setDonorContactDetailsDTO(DonorContactMapper.toDTO(donor.getDonorContactDetails().get(0)));
		}
		donorDTO.setDonorId(donor.getDonorId());
		donorDTO.setDonorUuid(donor.getDonorUuid());
		donorDTO.setFirstName(donor.getFirstName());
		donorDTO.setGender(donor.getGender());
		donorDTO.setLastDonationDate(donor.getLastDonationDate());
		donorDTO.setLastName(donor.getLastName());
		donorDTO.setMiddleName(donor.getMiddleName());
		donorDTO.setNextAvailableDate(donor.getNextAvailableDate());
		donorDTO.setSearchComment(donor.getSearchComment());
		return donorDTO;
		
		
	}
	public static List<DonorDTO> toDTOList(List<Donor> donorList)
	{
		List<DonorDTO> donorDTOList = new ArrayList<DonorDTO>();
		
		for (Donor donor : donorList)
		{
			DonorDTO donorDTO = toDTO(donor);
			donorDTOList.add(donorDTO);
		}
		return donorDTOList;
	}
	
	public static Donor toEntity(org.ngo.think.dm.service.domain.Donor donor) throws ParseException
	{
		Donor donorEntity = new Donor();
		
		donorEntity.setBirthDate(DateUtil.stringToDate(donor.getDateOfBirth()));
		donorEntity.setFirstName(donor.getFirstName());
		donorEntity.setLastName(donor.getLastName());
		donorEntity.setBloodGroup(donor.getBloodGroup());
		donorEntity.setCreatedBy("System");
		donorEntity.setCreatedDate(new Date());
		
		DonorContactDetail donorContactDetail =  DonorContactMapper.toEntity(donor);
		
		donorEntity.addDonorContactDetail(donorContactDetail);
		
		DonorAddressDetail donorAddressDetail = DonorAddressMapper.toEntity(donor);
		
		donorEntity.addDonorAddressDetail(donorAddressDetail);
		
		
		return donorEntity;
		
		
	}
	public static List<Donor> toEntityList(List<org.ngo.think.dm.service.domain.Donor> donorList) throws ParseException
	{
		List<Donor> entityDonorList = new ArrayList<Donor>();

		for (org.ngo.think.dm.service.domain.Donor donor : donorList)
		{
			Donor donorEntity = toEntity(donor);
			entityDonorList.add(donorEntity);
		}
		return entityDonorList;
	}

}
