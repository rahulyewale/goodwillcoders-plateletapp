package org.ngo.think.dm.common.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class SearchCommunicationHistoryResponseDTO implements Serializable
{
	private static final long serialVersionUID = 1L;

	private ArrayList<SearchCommunicationHistoryResultDTO> SearchCommunicationHistoryResponseList = new ArrayList<SearchCommunicationHistoryResultDTO>();
	
	private String confirmSMSText;

	public ArrayList<SearchCommunicationHistoryResultDTO> getSearchCommunicationHistoryResponseList()
	{
		return SearchCommunicationHistoryResponseList;
	}

	public void setSearchCommunicationHistoryResponseList(ArrayList<SearchCommunicationHistoryResultDTO> searchCommunicationHistoryResponseList)
	{
		SearchCommunicationHistoryResponseList = searchCommunicationHistoryResponseList;
	}

	public String getConfirmSMSText()
	{
		return confirmSMSText;
	}

	public void setConfirmSMSText(String confirmSMSText)
	{
		this.confirmSMSText = confirmSMSText;
	}

	
}
