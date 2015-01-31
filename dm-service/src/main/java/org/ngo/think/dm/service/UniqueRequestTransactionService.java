package org.ngo.think.dm.service;

import org.ngo.think.dm.common.dto.SearchDonorRequestDTO;
import org.ngo.think.dm.common.enums.RandomNumberType;
import org.ngo.think.dm.common.util.RandomNumberGenerator;
import org.ngo.think.dm.persistence.dao.UniqueRequestDAO;
import org.ngo.think.dm.persistence.entity.UniqueRequestTxn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueRequestTransactionService
{

	@Autowired
	UniqueRequestDAO requestDAO;

	public String getUniqueRequestTranactionID(SearchDonorRequestDTO searchDonorRequestDTO)
	{
		String uniqueRequestNumber = null;
		UniqueRequestTxn uniqueRequestTxn = requestDAO.getUniqueRequestTxnByDateAndCenter(searchDonorRequestDTO.getRequestDate(), searchDonorRequestDTO.getDonationCentre());
		
		if (null == uniqueRequestTxn)
		{
			uniqueRequestNumber = RandomNumberGenerator.generateRandomNumber(RandomNumberType.REQUEST_NUMBER);
		}
		else
		{
			uniqueRequestNumber = uniqueRequestTxn.getRequestId();
		}
		
		return uniqueRequestNumber;
	}

}
