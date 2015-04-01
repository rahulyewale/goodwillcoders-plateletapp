package org.ngo.think.dm.web.managebeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.ngo.think.dm.common.dto.DonationCenterDTO;
import org.ngo.think.dm.common.dto.DonorDTO;
import org.ngo.think.dm.common.dto.SearchDonorRequestDTO;
import org.ngo.think.dm.common.dto.SearchDonorResponseDTO;

@ManagedBean(name = "searchDonorResponseMB")
@SessionScoped
public class SearchDonorResponseMB implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<DonorDTO> searchDonorList = new ArrayList<DonorDTO>();
	private SearchDonorRequestDTO donorRequestDTO = new SearchDonorRequestDTO();
	private SearchDonorResponseDTO searchDonorResponseDTO = new SearchDonorResponseDTO();
	
	public DonationCenterDTO[] donationCenterDTOList = new DonationCenterDTO[50];
	
	private boolean donationCentersSet = false;
	

	public List<DonorDTO> getSearchDonorList()
	{
		return searchDonorList;
	}

	public void setSearchDonorList(List<DonorDTO> searchDonorList)
	{
		this.searchDonorList = searchDonorList;
	}

	public SearchDonorRequestDTO getDonorRequestDTO()
	{
		return donorRequestDTO;
	}

	public void setDonorRequestDTO(SearchDonorRequestDTO donorRequestDTO)
	{
		this.donorRequestDTO = donorRequestDTO;
	}

	public SearchDonorResponseDTO getSearchDonorResponseDTO()
	{
		return searchDonorResponseDTO;
	}

	public void setSearchDonorResponseDTO(SearchDonorResponseDTO searchDonorResponseDTO)
	{
		this.searchDonorResponseDTO = searchDonorResponseDTO;
	}

	public DonationCenterDTO[] getDonationCenterDTOList()
	{
		return donationCenterDTOList;
	}

	public void setDonationCenterDTOList(DonationCenterDTO[] donationCenterDTOList)
	{
		this.donationCenterDTOList = donationCenterDTOList;
	}

	public boolean isDonationCentersSet()
	{
		return donationCentersSet;
	}

	public void setDonationCentersSet(boolean donationCentersSet)
	{
		this.donationCentersSet = donationCentersSet;
	}
	

}
