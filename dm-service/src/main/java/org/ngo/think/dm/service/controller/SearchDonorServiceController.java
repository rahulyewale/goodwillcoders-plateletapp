package org.ngo.think.dm.service.controller;

import org.ngo.think.dm.common.dto.SearchDonorRequestDTO;
import org.ngo.think.dm.service.SearchDonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SearchDonorServiceController
{

	@Autowired
	SearchDonorService searchDonorService;
	
	
	public void searchDonor()
	{
		SearchDonorRequestDTO donorRequestDTO = new SearchDonorRequestDTO(); 
		searchDonorService.searchDonor(donorRequestDTO);
	}
	
}
