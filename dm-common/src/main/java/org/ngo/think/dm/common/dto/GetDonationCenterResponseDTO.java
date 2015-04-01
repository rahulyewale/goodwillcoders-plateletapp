package org.ngo.think.dm.common.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GetDonationCenterResponseDTO implements Serializable
{

	private List<DonationCenterDTO> donationCenterDTOList = new ArrayList<DonationCenterDTO>();

	public List<DonationCenterDTO> getDonationCenterDTOList()
	{
		return donationCenterDTOList;
	}

	public void setDonationCenterDTOList(List<DonationCenterDTO> donationCenterDTOList)
	{
		this.donationCenterDTOList = donationCenterDTOList;
	}

}
