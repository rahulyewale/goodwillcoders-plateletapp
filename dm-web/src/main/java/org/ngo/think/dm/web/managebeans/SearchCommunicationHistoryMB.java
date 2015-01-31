package org.ngo.think.dm.web.managebeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.ngo.think.dm.common.Context.ContextInfo;
import org.ngo.think.dm.common.communication.dto.ServiceRequest;
import org.ngo.think.dm.common.communication.dto.ServiceResponse;
import org.ngo.think.dm.common.constant.CommonConstants;
import org.ngo.think.dm.common.dto.SearchCommunicationHistoryRequestDTO;
import org.ngo.think.dm.common.dto.SearchCommunicationHistoryResponseDTO;
import org.ngo.think.dm.common.dto.SearchCommunicationHistoryResultDTO;
import org.ngo.think.dm.common.util.JsonUtil;
import org.ngo.think.dm.web.client.RestSeviceInvoker;
import org.ngo.think.dm.web.constant.WebConstant;

@SuppressWarnings("serial")
@ManagedBean(name = "srchCommHistMB")
@SessionScoped
public class SearchCommunicationHistoryMB implements Serializable
{

	private static final long serialVersionUID = 1L;

	private SearchCommunicationHistoryRequestDTO srchCommnHistReqDTO = new SearchCommunicationHistoryRequestDTO();

	private List<SearchCommunicationHistoryResultDTO> srchCommnHistResultList = new ArrayList<SearchCommunicationHistoryResultDTO>();
	
	

	public SearchCommunicationHistoryRequestDTO getSrchCommnHistReqDTO()
	{
		return srchCommnHistReqDTO;
	}

	public void setSrchCommnHistReqDTO(SearchCommunicationHistoryRequestDTO srchCommnHistReqDTO)
	{
		this.srchCommnHistReqDTO = srchCommnHistReqDTO;
	}

	public List<SearchCommunicationHistoryResultDTO> getSrchCommnHistResultList()
	{
		return srchCommnHistResultList;
	}

	public void setSrchCommnHistResultList(List<SearchCommunicationHistoryResultDTO> srchCommnHistResultList)
	{
		this.srchCommnHistResultList = srchCommnHistResultList;
	}

	
	public void searchCommunications()
	{
		
		
		try
		{
			ServiceRequest serviceRequest = new ServiceRequest(new ContextInfo(), CommonConstants.RequestKey.SEARCH_COMMUNICATION_HISTORY_REQUEST,srchCommnHistReqDTO);
			ServiceResponse serviceResponse = null;
			
			serviceResponse =  RestSeviceInvoker.invokeRestService(WebConstant.ServiceURL.COMMUNICATION_HISTORY_SEARCH_SERVICE_URL, serviceRequest);
			String jsonResponseString = JsonUtil.convertObjectToJson(serviceResponse.get(CommonConstants.ResponseKey.SEARCH_COMMUNICATION_HISTORY_RESPONSE));
			
			SearchCommunicationHistoryResponseDTO searchCommunicationHistoryResponse = (SearchCommunicationHistoryResponseDTO) JsonUtil.convertJsonToObject(jsonResponseString, SearchCommunicationHistoryResponseDTO.class);
			
			this.getSrchCommnHistResultList().clear();
			this.srchCommnHistResultList = searchCommunicationHistoryResponse.getSearchCommunicationHistoryResponseList();
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void confirmCommunications()
	{
		
		ArrayList<SearchCommunicationHistoryResultDTO> filteredSearch = filteredSrchCommnHistResultList(this.srchCommnHistResultList);
		
		SearchCommunicationHistoryResponseDTO confirmSearchCommunicationHistoryResponseDTO  = new SearchCommunicationHistoryResponseDTO();
		confirmSearchCommunicationHistoryResponseDTO.setSearchCommunicationHistoryResponseList(filteredSearch);
		
		try
		{
			ServiceRequest serviceRequest = new ServiceRequest(new ContextInfo(), CommonConstants.RequestKey.CONFIRM_COMMUNICATION_HISTORY_REQUEST,confirmSearchCommunicationHistoryResponseDTO);
			ServiceResponse serviceResponse = null;
			
			serviceResponse =  RestSeviceInvoker.invokeRestService(WebConstant.ServiceURL.COMMUNICATION_HISTORY_SEARCH_SERVICE_URL, serviceRequest);
			String jsonResponseString = JsonUtil.convertObjectToJson(serviceResponse.get(CommonConstants.ResponseKey.CONFIRM_COMMUNICATION_HISTORY_RESPONSE));
			
			SearchCommunicationHistoryResponseDTO searchCommunicationHistoryResponse = (SearchCommunicationHistoryResponseDTO) JsonUtil.convertJsonToObject(jsonResponseString, SearchCommunicationHistoryResponseDTO.class);
			
			this.getSrchCommnHistResultList().clear();
			this.setSrchCommnHistResultList(searchCommunicationHistoryResponse.getSearchCommunicationHistoryResponseList());
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	private ArrayList<SearchCommunicationHistoryResultDTO> filteredSrchCommnHistResultList (List<SearchCommunicationHistoryResultDTO> allSrchCommnHistResultList)
	{
		ArrayList<SearchCommunicationHistoryResultDTO> filteredSrchCommnHistResultList = new ArrayList<SearchCommunicationHistoryResultDTO>();
		for(SearchCommunicationHistoryResultDTO srchHist : allSrchCommnHistResultList)
		{
			if(srchHist.isHistorySelected())
			{
				filteredSrchCommnHistResultList.add(srchHist);
			}
		}
		
		return filteredSrchCommnHistResultList;
	}
	
	public void cancelCommunications()
	{
		
	}
	
	
}
