package org.ngo.think.dm.web.managebeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.ngo.think.dm.common.dto.DonorDTO;
import org.ngo.think.dm.common.dto.SearchDonorRequestDTO;

@SuppressWarnings("serial")
@ManagedBean(name="searchDonorMB")
@SessionScoped
public class SearchDonorMB implements Serializable {

private SearchDonorRequestDTO donorRequestDTO = new SearchDonorRequestDTO();
private List<DonorDTO> searchDonorList = new ArrayList<DonorDTO>();

	public SearchDonorMB() {
		// TODO Auto-generated constructor stub
	} 
	 
	public void searchDonor()
	{
		DonorDTO donorDTO = new DonorDTO();
		donorDTO.setFirstName("Rajiv");
		donorDTO.setLastName("Vaidya");
		this.getSearchDonorList().clear();
		this.getSearchDonorList().add(donorDTO);
		
		
	}
	
	public void sendSMS()
	{
		System.out.println("generate register id R_32413");
		System.out.println("sending sms ");
	}
	
	public void confirmedDonor()
	{
		System.out.println("confirm donor");
	}

	public List<DonorDTO> getSearchDonorList() {
		
		return searchDonorList;
	}

	public void setSearchDonorList(List<DonorDTO> searchDonorList) {
		this.searchDonorList = searchDonorList;
	}

	public SearchDonorRequestDTO getDonorRequestDTO() {
		return donorRequestDTO;
	}

	public void setDonorRequestDTO(SearchDonorRequestDTO donorRequestDTO) {
		this.donorRequestDTO = donorRequestDTO;
	}
	
	
}
