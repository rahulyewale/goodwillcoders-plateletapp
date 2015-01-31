package org.ngo.think.dm.common.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class SearchCommunicationHistoryResponseDTO implements Serializable
{
	private static final long serialVersionUID = 1L;

	private ArrayList<SearchCommunicationHistoryResultDTO> SearchCommunicationHistoryResponseList = new ArrayList<SearchCommunicationHistoryResultDTO>();

	public ArrayList<SearchCommunicationHistoryResultDTO> getSearchCommunicationHistoryResponseList()
	{
		return SearchCommunicationHistoryResponseList;
	}

	public void setSearchCommunicationHistoryResponseList(ArrayList<SearchCommunicationHistoryResultDTO> searchCommunicationHistoryResponseList)
	{
		SearchCommunicationHistoryResponseList = searchCommunicationHistoryResponseList;
	}

}
