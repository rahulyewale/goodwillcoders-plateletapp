package org.ngo.think.dm.web.managebeans;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.ngo.think.dm.common.dto.DonorDTO;
import org.ngo.think.dm.common.dto.SearchDonorRequestDTO;
import org.ngo.think.dm.common.dto.SearchDonorResponseDTO;

@ManagedBean(name = "dashbord")
@RequestScoped
public class DashbordMB
{

	@ManagedProperty(value="#{searchDonorResponseMB}")
	private SearchDonorResponseMB searchDonorResponseMB = new SearchDonorResponseMB();

	
	public String navigateToSearchDonor()
	{
		searchDonorResponseMB.setSearchDonorList(new ArrayList<DonorDTO>());
		searchDonorResponseMB.setDonorRequestDTO(new SearchDonorRequestDTO());
		searchDonorResponseMB.setSearchDonorResponseDTO(new SearchDonorResponseDTO());

		System.out.println("navigating to search donor");
		return "success";
	}

	public String navigateToDashbord()
	{
		// fetching the center list
		System.out.println("navigating to dashbord");
		return "success";
	}

	public String navigateToSearchCommunicationHistory()
	{
		System.out.println("navigating to search communication history");
		return "success";
	}
	
	public String navigateToExcelUpload()
	{
		return "success";
	}
	
	public SearchDonorResponseMB getSearchDonorResponseMB()
	{
		return searchDonorResponseMB;
	}

	public void setSearchDonorResponseMB(SearchDonorResponseMB searchDonorResponseMB)
	{
		this.searchDonorResponseMB = searchDonorResponseMB;
	}
}
