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

@ManagedBean(name = "bean")
@RequestScoped
public class DashboardMB
{
	@ManagedProperty(value = "#{searchDonorResponseMB}")
	private SearchDonorResponseMB searchDonorResponseMB = new SearchDonorResponseMB();

	private String page = "donorexcelimport";
	
	private boolean searchdonor = false;
	private boolean searchcommunicationhistory = false;
	private boolean uploaddonor = false;
	
	private int currentTab = 2;

	public int getCurrentTab()
	{
		return currentTab;
	}

	public void setCurrentTab(int currentTab)
	{
		this.currentTab = currentTab;
	}

	public String getPage()
	{
		return page;
	}

	public void setPage(String page)
	{
		this.page = page;
	}

/*	public void setPageInfo(String page, int currentTab)
	{
		this.page = page;
		this.currentTab = currentTab;

		if (page.equals("searchdonor"))
		{

			if (!searchDonorResponseMB.isDonationCentersSet())
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
			}
			searchDonorResponseMB.setSearchDonorList(new ArrayList<DonorDTO>());
			searchDonorResponseMB.setDonorRequestDTO(new SearchDonorRequestDTO());
			searchDonorResponseMB.setSearchDonorResponseDTO(new SearchDonorResponseDTO());

		}

	}*/
	
	
	public void setPageInfo(String page, int currentTab)
	{
		this.page = page;
		this.currentTab = currentTab;
		
		if (page.equals("searchcommunicationhistory"))
		{
			setSearchcommunicationhistory(true);
		}

		if (page.equals("donorexcelimport"))
		{
			setUploaddonor(true);
		}
		

		if (page.equals("searchdonor"))
		{
			setSearchdonor(true);
			if (!searchDonorResponseMB.isDonationCentersSet())
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
			}
			searchDonorResponseMB.setSearchDonorList(new ArrayList<DonorDTO>());
			searchDonorResponseMB.setDonorRequestDTO(new SearchDonorRequestDTO());
			searchDonorResponseMB.setSearchDonorResponseDTO(new SearchDonorResponseDTO());

		}

	}

	public SearchDonorResponseMB getSearchDonorResponseMB()
	{
		return searchDonorResponseMB;
	}

	public void setSearchDonorResponseMB(SearchDonorResponseMB searchDonorResponseMB)
	{
		this.searchDonorResponseMB = searchDonorResponseMB;
	}

	public boolean isSearchdonor()
	{
		return searchdonor;
	}

	public void setSearchdonor(boolean searchdonor)
	{
		this.searchdonor = searchdonor;
	}

	public boolean isSearchcommunicationhistory()
	{
		return searchcommunicationhistory;
	}

	public void setSearchcommunicationhistory(boolean searchcommunicationhistory)
	{
		this.searchcommunicationhistory = searchcommunicationhistory;
	}

	public boolean isUploaddonor()
	{
		return uploaddonor;
	}

	public void setUploaddonor(boolean uploaddonor)
	{
		this.uploaddonor = uploaddonor;
	}
	
	
}
