package org.ngo.think.dm.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.ngo.think.dm.common.dto.DonorAppointmentDTO;
import org.ngo.think.dm.common.dto.DonorDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommunicationHistoryService
{

	@Transactional
	public void sendSMSForSearchedDonors(DonorAppointmentDTO donorAppointmentDTO)
	{
		
		List<String> donorsContacts = new ArrayList<String>();
				
		
		List<DonorDTO> donors = donorAppointmentDTO.getDonors();
		for (Iterator<DonorDTO> iterator = donors.iterator(); iterator.hasNext();)
		{
			DonorDTO donorDTO = (DonorDTO) iterator.next();
			
			
		}
	}
}
