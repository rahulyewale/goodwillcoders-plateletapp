package org.ngo.think.dm.web.managebeans;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.ngo.think.dm.common.Context.ContextInfo;
import org.ngo.think.dm.common.communication.dto.ServiceRequest;
import org.ngo.think.dm.common.communication.dto.ServiceResponse;
import org.ngo.think.dm.common.constant.CommonConstants;
import org.ngo.think.dm.common.dto.DonationCenterDTO;
import org.ngo.think.dm.common.dto.DonorDTO;
import org.ngo.think.dm.common.dto.GetDonationCenterResponseDTO;
import org.ngo.think.dm.common.dto.SearchDonorRequestDTO;
import org.ngo.think.dm.common.dto.SearchDonorResponseDTO;
import org.ngo.think.dm.common.util.JsonUtil;
import org.ngo.think.dm.web.client.RestSeviceInvoker;
import org.ngo.think.dm.web.constant.WebConstant;

@ManagedBean(name = "dashbord")
@RequestScoped
public class DashbordMB
{

	@ManagedProperty(value="#{searchDonorResponseMB}")
	private SearchDonorResponseMB searchDonorResponseMB = new SearchDonorResponseMB();

	
	public String navigateToSearchDonor()
	{
		ServiceRequest serviceRequest = new ServiceRequest(new ContextInfo());
		String serviceResponseString = null;
		ServiceResponse serviceResponse = null;
		GetDonationCenterResponseDTO donationCenterResponseDTO = null;
		try
		{
			serviceResponse = RestSeviceInvoker.invokeRestService(WebConstant.ServiceURL.GET_DONATION_CENTERS_URL, serviceRequest);

			serviceResponseString = JsonUtil.convertObjectToJson(serviceResponse.get(CommonConstants.ResponseKey.SEARCH_DOATION_CENTER_RESPONSE));
			donationCenterResponseDTO = (GetDonationCenterResponseDTO) JsonUtil.convertJsonToObject(serviceResponseString, GetDonationCenterResponseDTO.class);

		}
		catch (Exception e)
		{

			e.printStackTrace();
		}
		
		DonationCenterDTO[] centerDTOs  = new DonationCenterDTO[donationCenterResponseDTO.getDonationCenterDTOList().size()];
		for (int i = 0; i < donationCenterResponseDTO.getDonationCenterDTOList().size(); i++)
		{
			centerDTOs[i] = donationCenterResponseDTO.getDonationCenterDTOList().get(i);
		}
		searchDonorResponseMB.setDonationCenterDTOList(centerDTOs);
		searchDonorResponseMB.setDonationCentersSet(true);
		
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
