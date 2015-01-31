package org.ngo.think.dm.service.mapper;

import java.util.ArrayList;
import java.util.List;

import org.ngo.think.dm.common.dto.DonorDTO;
import org.ngo.think.dm.persistence.entity.Donor;

public class DonorMapper
{

	public static DonorDTO toDTO(Donor donor)
	{
		DonorDTO donorDTO = new DonorDTO();
		donorDTO.setBirthDate(donor.getBirthDate());
		donorDTO.setBloodGroup(donor.getBloodGroup());
		donorDTO.setDistanceKm(donor.getDistanceInKm());
		donorDTO.setDonationComponentType(donor.getDonationComponentType());
		donorDTO.setDonorAddressDetailsDTO(DonorAddressMapper.toDTO(donor.getDonorAddressDetails().get(0)));
		donorDTO.setDonorContactDetailsDTO(DonorContactMapper.toDTO(donor.getDonorContactDetails().get(0)));
		donorDTO.setDonorId(donor.getDonorId());
		donorDTO.setDonorUuid(donor.getDonorUuid());
		donorDTO.setFirstName(donor.getFirstName());
		donorDTO.setGender(donor.getGender());
		donorDTO.setLastDonationDate(donor.getLastDonationDate());
		donorDTO.setLastName(donor.getLastName());
		donorDTO.setMiddleName(donor.getMiddleName());
		donorDTO.setNextAvailableDate(donor.getNextAvailableDate());
		
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

}
