package org.ngo.think.dm.web.managebeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.ngo.think.dm.common.dto.SearchCommunicationHistoryRequestDTO;
import org.ngo.think.dm.common.dto.SearchCommunicationHistoryResultDTO;

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

}
