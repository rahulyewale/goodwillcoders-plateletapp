package org.ngo.think.dm.service;

import java.util.ArrayList;
import java.util.List;

import org.ngo.think.dm.common.dto.DonorDTO;
import org.ngo.think.dm.common.dto.SearchDonorRequestDTO;
import org.ngo.think.dm.common.dto.SearchDonorResponseDTO;
import org.ngo.think.dm.persistence.dao.DonorDAO;
import org.ngo.think.dm.persistence.entity.Donor;
import org.ngo.think.dm.service.domain.DonorFilter;
import org.ngo.think.dm.service.mapper.DonorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SearchDonorService
{

	@Autowired
	DonorFilter donorFilter;
	
	@Autowired
	UniqueRequestTransactionService uniqueRequestTransactionService;
	
	@Autowired
	DonorDAO donorDAO;
	
	@Transactional
	public SearchDonorResponseDTO searchDonor(SearchDonorRequestDTO searchDonorRequestDTO)
	{
		
		
		
		//Call DAOImpl to get donor master List
		List<Donor> donorList = new ArrayList<Donor>();
		 donorFilter.filterDonorsBasedOnSearchCriteria(searchDonorRequestDTO,donorList);
		 
		 List<DonorDTO> donorDTOList = DonorMapper.toDTOList(donorList);
		 
		 if(donorList.size()>0)
		 {
			 //set and return sms
			 
		 }
		 
		 String uniqueRequestNumber = uniqueRequestTransactionService.getUniqueRequestTranactionID(searchDonorRequestDTO);
		 
		 
		 SearchDonorResponseDTO searchDonorResponseDTO = new SearchDonorResponseDTO();
		 searchDonorResponseDTO.setConfirmSmsText(confirmSmsText);
		 searchDonorResponseDTO.setIntialSmsText(intialSmsText);
		 searchDonorResponseDTO.setDonorDTOList(donorDTOList);
		 return null;
		
		
	}
}

