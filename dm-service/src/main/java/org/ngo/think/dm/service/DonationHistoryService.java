package org.ngo.think.dm.service;

import org.ngo.think.dm.common.communication.dto.ResponseData;
import org.ngo.think.dm.common.dto.DonationHistoryDTO;
import org.ngo.think.dm.persistence.dao.DonationHistoryDAO;
import org.ngo.think.dm.persistence.entity.DonationHistory;
import org.ngo.think.dm.service.mapper.DonationHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DonationHistoryService
{

	@Autowired
	private DonationHistoryDAO donationHistoryDAO;

	@Transactional
	public ResponseData addDonationHistory(DonationHistoryDTO donationHistoryDTO)
	{
		DonationHistory donationHistory = DonationHistoryMapper.toEntity(donationHistoryDTO);

		try
		{
			donationHistoryDAO.save(donationHistory);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return ResponseData.successResponseData;
	}

}
