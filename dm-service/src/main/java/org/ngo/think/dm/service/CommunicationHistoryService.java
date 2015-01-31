package org.ngo.think.dm.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.ngo.think.dm.common.dto.DonorAppointmentDTO;
import org.ngo.think.dm.common.dto.DonorDTO;
import org.ngo.think.dm.persistence.dao.CommunicationHistoryDAO;
import org.ngo.think.dm.persistence.entity.CommunicationHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommunicationHistoryService
{
	
	@Autowired
	private CommunicationHistoryDAO communicationHistoryDAO;

	@Transactional
	public void sendSMSForSearchedDonors(DonorAppointmentDTO donorAppointmentDTO)
	{
		
		List<String> donorsContacts = new ArrayList<String>();
				
		
		List<DonorDTO> donors = donorAppointmentDTO.getDonors();
		for (Iterator<DonorDTO> iterator = donors.iterator(); iterator.hasNext();)
		{
			DonorDTO donorDTO = (DonorDTO) iterator.next();
			
			List<CommunicationHistory> communicationHistories = communicationHistoryDAO.getCommunicationHistoryForGivenCriteria(donorDTO.getDonorId(), donorAppointmentDTO);
			
			if(communicationHistories.isEmpty())
			{
				
			}
			else
			{
				//STOP already sent
			}
		}
	}
}
