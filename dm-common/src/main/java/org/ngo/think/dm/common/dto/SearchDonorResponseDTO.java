package org.ngo.think.dm.common.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SearchDonorResponseDTO implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<DonorDTO> donorDTOList = new ArrayList<DonorDTO>();

	private String intialSmsText;

	private String confirmSmsText;

	private String uniqueRequestId;
	
	private DonationCenterDTO donationCenterDTO;

	public List<DonorDTO> getDonorDTOList()
	{
		return donorDTOList;
	}

	public void setDonorDTOList(List<DonorDTO> donorDTOList)
	{
		this.donorDTOList = donorDTOList;
	}

	public String getIntialSmsText()
	{
		return intialSmsText;
	}

	public void setIntialSmsText(String intialSmsText)
	{
		this.intialSmsText = intialSmsText;
	}

	public String getConfirmSmsText()
	{
		return confirmSmsText;
	}

	public void setConfirmSmsText(String confirmSmsText)
	{
		this.confirmSmsText = confirmSmsText;
	}

	public String getUniqueRequestId()
	{
		return uniqueRequestId;
	}

	public void setUniqueRequestId(String uniqueRequestId)
	{
		this.uniqueRequestId = uniqueRequestId;
	}

	public DonationCenterDTO getDonationCenterDTO()
	{
		return donationCenterDTO;
	}

	public void setDonationCenterDTO(DonationCenterDTO donationCenterDTO)
	{
		this.donationCenterDTO = donationCenterDTO;
	}

	
}
