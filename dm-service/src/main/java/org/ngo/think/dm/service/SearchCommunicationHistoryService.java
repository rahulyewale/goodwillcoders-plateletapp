package org.ngo.think.dm.service;

import org.ngo.think.dm.common.dto.SearchCommunicationHistoryRequestDTO;
import org.ngo.think.dm.common.dto.SearchCommunicationHistoryResponseDTO;
import org.ngo.think.dm.persistence.dao.CommunicationHistoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class SearchCommunicationHistoryService
{
	
	@Autowired
	CommunicationHistoryDAO communicationHistoryDAO;
	
	
	
	@Transactional
	public SearchCommunicationHistoryResponseDTO searchcommunicationhistory(SearchCommunicationHistoryRequestDTO searchCommunicationHistoryRequest)
	{
		System.out.println("In SearchCommunicationHistoryService : searchcommunicationhistory !!!");

		return null;
	}

}
