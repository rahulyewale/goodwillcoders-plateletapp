package org.ngo.think.dm.web.managebeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.ngo.think.dm.common.Context.ContextInfo;
import org.ngo.think.dm.common.communication.dto.ServiceRequest;
import org.ngo.think.dm.common.communication.dto.ServiceResponse;
import org.ngo.think.dm.common.constant.CommonConstants;
import org.ngo.think.dm.common.dto.GetRequestListInputDTO;
import org.ngo.think.dm.common.dto.GetRequestListResponse;
import org.ngo.think.dm.common.dto.UniqueRequestDTO;
import org.ngo.think.dm.common.util.DateUtil;
import org.ngo.think.dm.common.util.JsonUtil;
import org.ngo.think.dm.web.client.RestSeviceInvoker;
import org.ngo.think.dm.web.constant.WebConstant;

/**
 * @author rahulsy
 *MB for RequestList Page.
 */
@ManagedBean(name = "requestListMB")
@ViewScoped
public class RequestListMB implements Serializable
{

	private static final long serialVersionUID = 1L;


	private GetRequestListInputDTO requestListInputDTO = new GetRequestListInputDTO();
	
	private List<UniqueRequestDTO> requestDTOList = new ArrayList<UniqueRequestDTO>();
	
	@ManagedProperty(value = "#{cachedDataMB}")
	private CachedDataMB cachedDataMB = new CachedDataMB();
	
	
	public CachedDataMB getCachedDataMB()
	{
		return cachedDataMB;
	}


	public void setCachedDataMB(CachedDataMB cachedDataMB)
	{
		this.cachedDataMB = cachedDataMB;
	}


	public String navigateToRequestList()
	{
		System.out.println("navigating to request list");
		
		setRequestListInputDTO(new GetRequestListInputDTO());
		setRequestDTOList(new ArrayList<UniqueRequestDTO>());
		

		Date donationRequestFromDate = new Date();

		Date donationRequestToDate = DateUtil.addDaysToDate(donationRequestFromDate, 91);

		String status = "OPEN";

		getRequestListInputDTO().setDonationRequestFromDate(donationRequestFromDate);
		getRequestListInputDTO().setDonationRequestToDate(donationRequestToDate);
		getRequestListInputDTO().setStatus(status);
		
		searchRequestList(null);
		
		return "success";
	}
	
	
	public List<UniqueRequestDTO> getRequestDTOList()
	{
		return requestDTOList;
	}


	public void setRequestDTOList(List<UniqueRequestDTO> requestDTOList)
	{
		this.requestDTOList = requestDTOList;
	}
	

	public void searchRequestList(String calledFromPage)
	{
		ServiceRequest serviceRequest = new ServiceRequest(new ContextInfo());
		if (null != calledFromPage)
		{
			getRequestListInputDTO().setDonationCenterId(getRequestListInputDTO().getDonationCenterId());
		}
		
		serviceRequest.add(CommonConstants.RequestKey.GET_REQUEST_LIST_REQUEST, getRequestListInputDTO());
		String serviceResponseString = null;
		ServiceResponse serviceResponse = null;
		GetRequestListResponse getRequestListResponse = null;
		try
		{
			serviceResponse = RestSeviceInvoker.invokeRestService(WebConstant.ServiceURL.GET_REQUEST_LIST_URL, serviceRequest);

			serviceResponseString = JsonUtil.convertObjectToJson(serviceResponse.get(CommonConstants.ResponseKey.GET_REQUEST_LIST_RESPONSE));
			getRequestListResponse = (GetRequestListResponse) JsonUtil.convertJsonToObject(serviceResponseString, GetRequestListResponse.class);

		}
		catch (Exception e)
		{

			e.printStackTrace();
		}

		setRequestDTOList(getRequestListResponse.getRequestListOutputList());
	}


	public GetRequestListInputDTO getRequestListInputDTO()
	{
		return requestListInputDTO;
	}


	public void setRequestListInputDTO(GetRequestListInputDTO getRequestListInputDTO)
	{
		this.requestListInputDTO = getRequestListInputDTO;
	}

	
}
