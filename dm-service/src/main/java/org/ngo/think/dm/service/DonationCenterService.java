package org.ngo.think.dm.service;

import java.util.List;

import org.ngo.think.dm.common.dto.DonationCenterDTO;
import org.ngo.think.dm.common.dto.GetDonationCenterResponseDTO;
import org.ngo.think.dm.persistence.dao.DonationCenterDAO;
import org.ngo.think.dm.persistence.entity.DonationCenter;
import org.ngo.think.dm.service.mapper.DonationCenterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DonationCenterService
{

	@Autowired
	private DonationCenterDAO centerDAO;

	public GetDonationCenterResponseDTO getDonationCenters()
	{
		List<DonationCenter> donationCenters = centerDAO.getDonationCenters();

		List<DonationCenterDTO> centerDTOList = DonationCenterMapper.toDTOList(donationCenters);

		GetDonationCenterResponseDTO donationCenterResponseDTO = new GetDonationCenterResponseDTO();

		donationCenterResponseDTO.setDonationCenterDTOList(centerDTOList);

		return donationCenterResponseDTO;

	}
}
