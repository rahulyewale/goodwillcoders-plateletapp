package org.ngo.think.dm.web.managebeans;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.ngo.think.dm.common.dto.GetRequestListInputDTO;
import org.ngo.think.dm.common.dto.SearchDonorRequestDTO;
import org.ngo.think.dm.common.dto.UniqueRequestDTO;
import org.ngo.think.dm.common.util.DateUtil;

@ManagedBean(name = "dashbord")
@SessionScoped
public class DashbordMB implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GetRequestListInputDTO getRequestListInputDTO;

	private UniqueRequestDTO selectedRequestDTO;

	private SearchDonorRequestDTO searchDonorRequestDTO;


	public GetRequestListInputDTO getGetRequestListInputDTO()
	{
		System.out.println("getGetRequestListInputDTO called");
		return getRequestListInputDTO;
	}

	public void setGetRequestListInputDTO(GetRequestListInputDTO getRequestListInputDTO)
	{
		this.getRequestListInputDTO = getRequestListInputDTO;
	}

	public UniqueRequestDTO getSelectedRequestDTO()
	{
		return selectedRequestDTO;
	}

	public void setSelectedRequestDTO(UniqueRequestDTO selectedRequestDTO)
	{
		this.selectedRequestDTO = selectedRequestDTO;
	}

	public SearchDonorRequestDTO getSearchDonorRequestDTO()
	{
		return searchDonorRequestDTO;
	}

	public void setSearchDonorRequestDTO(SearchDonorRequestDTO searchDonorRequestDTO)
	{
		this.searchDonorRequestDTO = searchDonorRequestDTO;
	}


	public String navigateToSearchDonor()
	{
		System.out.println("navigating to search donor");
		return "success";
	}

	public String navigateToSearchDonorOnMap()
	{
		System.out.println("navigating to search donor using map");
		return "success";
	}
	
	public String navigateToDashbord()
	{
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

	public String navigateToRequestList()
	{
		System.out.println("navigating to request list");

		Date donationRequestFromDate = new Date();

		Date donationRequestToDate = DateUtil.addDaysToDate(donationRequestFromDate, 91);

		String status = "OPEN";

		this.getRequestListInputDTO = new GetRequestListInputDTO();
		
		
		this.getRequestListInputDTO.setDonationRequestFromDate(donationRequestFromDate);
		this.getRequestListInputDTO.setDonationRequestToDate(donationRequestToDate);
		this.getRequestListInputDTO.setStatus(status);

		return "success";
	}

	public void setSearchDonorCriteria(UniqueRequestDTO requestDTO)
	{
		try
		{
			this.searchDonorRequestDTO= new SearchDonorRequestDTO();   
			getSearchDonorRequestDTO().setRequestDate(DateUtil.stringToDate(requestDTO.getRequestedDate(), "dd-MMM-yyyy"));
			getSearchDonorRequestDTO().setPatientname(requestDTO.getPatientName());
		}
		catch (ParseException e1)
		{
			e1.printStackTrace();
		}

		getSearchDonorRequestDTO().setBloodGroup(requestDTO.getBloodGroup());
		getSearchDonorRequestDTO().setDonationCentre(requestDTO.getDonationCenterDTO().getDonationCenterId());
		getSearchDonorRequestDTO().setNotDonatedInLastMonthsCount(0);
		getSearchDonorRequestDTO().setNumberOfDonationsLessThanCount(24);

	}

}
