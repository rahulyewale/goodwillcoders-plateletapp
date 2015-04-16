package org.ngo.think.dm.web.managebeans;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.ngo.think.dm.common.Context.ContextInfo;
import org.ngo.think.dm.common.communication.dto.ServiceRequest;
import org.ngo.think.dm.common.communication.dto.ServiceResponse;
import org.ngo.think.dm.common.constant.CommonConstants;
import org.ngo.think.dm.common.dto.DonationCenterDTO;
import org.ngo.think.dm.common.dto.GetDonationCenterResponseDTO;
import org.ngo.think.dm.common.util.JsonUtil;
import org.ngo.think.dm.web.client.RestSeviceInvoker;
import org.ngo.think.dm.web.constant.WebConstant;

@ManagedBean(name = "cachedDataMB")
@SessionScoped
public class CachedDataMB implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	public DonationCenterDTO[] donationCenterDTOs = new DonationCenterDTO[50];
	
	private boolean areDonationCentersFetched = false;
	
	@PostConstruct
	public void init()
	{
		System.out.println("START - Post Construct");
		
		if(!areDonationCentersFetched)
		{
			fetchDonationCenters();
		}
		else
		{
			System.out.println("Donation Centers already fetched, Total:"+donationCenterDTOs.length);
		}
		
		System.out.println("END - Post Construct");
	}
	
	private GetDonationCenterResponseDTO fetchDonationCenters()
	{
		System.out.println("START - fetching donation centers");
		
		ServiceRequest serviceRequest = new ServiceRequest(new ContextInfo());
		String serviceResponseString = null;
		ServiceResponse serviceResponse = null;
		GetDonationCenterResponseDTO donationCenterResponseDTO = null;
		
		try
		{
			serviceResponse = RestSeviceInvoker.invokeRestService(WebConstant.ServiceURL.GET_DONATION_CENTERS_URL, serviceRequest);
			serviceResponseString = JsonUtil.convertObjectToJson(serviceResponse.get(CommonConstants.ResponseKey.SEARCH_DOATION_CENTER_RESPONSE));
			donationCenterResponseDTO = (GetDonationCenterResponseDTO) JsonUtil.convertJsonToObject(serviceResponseString, GetDonationCenterResponseDTO.class);
			
			List<DonationCenterDTO> donationCenters = donationCenterResponseDTO.getDonationCenterDTOList();
			
			donationCenterDTOs = donationCenters.toArray(new DonationCenterDTO[donationCenters.size()]);
			/*int index = 0;
			for (Iterator<DonationCenterDTO> iterator = donationCenters.iterator(); iterator.hasNext();) 
			{
				DonationCenterDTO donationCenterDTO = iterator.next();
				donationCenterDTOs[index] = donationCenterDTO;
				index ++;
			}*/
			areDonationCentersFetched = true;
		}
		catch (Exception e)
		{
			System.out.println("Exception occurred while fetching donation centers..");
			e.printStackTrace();
		}
		System.out.println("END - fetching donation centers");
		return donationCenterResponseDTO;
	}
	
	public DonationCenterDTO[] getDonationCenterDTOs() {
		return donationCenterDTOs;
	}

	public void setDonationCenterDTOs(DonationCenterDTO[] donationCenterDTOs) {
		this.donationCenterDTOs = donationCenterDTOs;
	}

	public boolean areDonationCentersFetched() {
		return areDonationCentersFetched;
	}

	public void setAreDonationCentersFetched(boolean areDonationCentersFetched) {
		this.areDonationCentersFetched = areDonationCentersFetched;
	}
}
