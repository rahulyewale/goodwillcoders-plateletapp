package org.ngo.think.dm.service.mapper;

import java.util.ArrayList;
import java.util.List;

import org.ngo.think.dm.common.dto.DonationCenterDTO;
import org.ngo.think.dm.persistence.entity.DonationCenter;

public class DonationCenterMapper
{

	public static DonationCenterDTO toDTO(DonationCenter donationCenterEntity)
	{
		DonationCenterDTO donationCenterDTO = new DonationCenterDTO();

		donationCenterDTO.setDonationCenterName(donationCenterEntity.getDonationCenterName());
		donationCenterDTO.setCity(donationCenterEntity.getCity());
		donationCenterDTO.setDonationCenterId(donationCenterEntity.getDonationCenterId());
		donationCenterDTO.setLattitude(donationCenterEntity.getLattitude());
		donationCenterDTO.setLongitude(donationCenterEntity.getLongitude());
		donationCenterDTO.setDonationCenterId(donationCenterEntity.getDonationCenterId());

		return donationCenterDTO;
	}

	public static List<DonationCenterDTO> toDTOList(List<DonationCenter> donationCenters)
	{
		List<DonationCenterDTO> centerDTOList = new ArrayList<DonationCenterDTO>();

		for (DonationCenter donationCenter : donationCenters)
		{
			DonationCenterDTO donationCenterDTO = toDTO(donationCenter);
			centerDTOList.add(donationCenterDTO);
		}

		return centerDTOList;
	}

}
