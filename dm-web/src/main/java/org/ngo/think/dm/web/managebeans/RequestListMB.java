package org.ngo.think.dm.web.managebeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.ngo.think.dm.common.dto.GetRequestListInputDTO;
import org.ngo.think.dm.common.dto.UniqueRequestDTO;

@ManagedBean(name = "requestListMB")
@SessionScoped
public class RequestListMB implements Serializable
{

	private static final long serialVersionUID = 1L;


	private GetRequestListInputDTO requestListInputDTO = new GetRequestListInputDTO();
	
	private List<UniqueRequestDTO> requestDTOList = new ArrayList<UniqueRequestDTO>();

	
	public List<UniqueRequestDTO> getRequestDTOList()
	{
		return requestDTOList;
	}


	public void setRequestDTOList(List<UniqueRequestDTO> requestDTOList)
	{
		this.requestDTOList = requestDTOList;
	}


	public void searchCommunications()
	{/*
		
		
		try
		{
			ServiceRequest serviceRequest = new ServiceRequest(new ContextInfo(), CommonConstants.RequestKey.SEARCH_COMMUNICATION_HISTORY_REQUEST,srchCommnHistReqDTO);
			ServiceResponse serviceResponse = null;
			
			serviceResponse =  RestSeviceInvoker.invokeRestService(WebConstant.ServiceURL.COMMUNICATION_HISTORY_SEARCH_SERVICE_URL, serviceRequest);
			String jsonResponseString = JsonUtil.convertObjectToJson(serviceResponse.get(CommonConstants.ResponseKey.SEARCH_COMMUNICATION_HISTORY_RESPONSE));
			
			SearchCommunicationHistoryResponseDTO searchCommunicationHistoryResponse = (SearchCommunicationHistoryResponseDTO) JsonUtil.convertJsonToObject(jsonResponseString, SearchCommunicationHistoryResponseDTO.class);
			
		//	this.getSrchCommnHistResultList().clear();
			this.srchCommnHistResultList = searchCommunicationHistoryResponse.getSearchCommunicationHistoryResponseList();
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	*/}


	public GetRequestListInputDTO getRequestListInputDTO()
	{
		return requestListInputDTO;
	}


	public void setRequestListInputDTO(GetRequestListInputDTO getRequestListInputDTO)
	{
		this.requestListInputDTO = getRequestListInputDTO;
	}
	
	/*public void confirmCommunications()
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
		
	}*/
	
	/*private ArrayList<SearchCommunicationHistoryResultDTO> filteredSrchCommnHistResultList (List<SearchCommunicationHistoryResultDTO> allSrchCommnHistResultList)
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
	*/
	/*public void cancelCommunications()
	{
		
	}*/
	
	
}
