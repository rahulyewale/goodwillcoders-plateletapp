package org.ngo.think.dm.service;

import java.util.Collections;
import java.util.List;

import org.ngo.think.dm.common.constant.RequestStatus;
import org.ngo.think.dm.common.dto.GetRequestListInputDTO;
import org.ngo.think.dm.common.dto.SearchDonorRequestDTO;
import org.ngo.think.dm.common.dto.UniqueRequestDTO;
import org.ngo.think.dm.common.enums.RandomNumberType;
import org.ngo.think.dm.common.util.RandomNumberGenerator;
import org.ngo.think.dm.persistence.dao.UniqueRequestDAO;
import org.ngo.think.dm.persistence.entity.DonationCenter;
import org.ngo.think.dm.persistence.entity.UniqueRequestTxn;
import org.ngo.think.dm.service.domain.DateComparator;
import org.ngo.think.dm.service.mapper.RequestMapper;
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
		UniqueRequestTxn uniqueRequestTxn = requestDAO.getUniqueRequestTxnByDateAndCenter(searchDonorRequestDTO.getRequestDate(), searchDonorRequestDTO.getDonationCentre(), searchDonorRequestDTO.getBloodGroup());
		
		if (null == uniqueRequestTxn)
		{
			uniqueRequestNumber = RandomNumberGenerator.generateRandomNumber(RandomNumberType.REQUEST_NUMBER);
			try
			{
				UniqueRequestTxn uniqueRequestTxn2 = new UniqueRequestTxn();
				DonationCenter donationCenter = new DonationCenter();
				donationCenter.setDonationCenterId(searchDonorRequestDTO.getDonationCentre());
				uniqueRequestTxn2.setDonationCenter(donationCenter);
				uniqueRequestTxn2.setRequestDate(searchDonorRequestDTO.getRequestDate());
				uniqueRequestTxn2.setRequestId(uniqueRequestNumber);
				uniqueRequestTxn2.setBloodGroup(searchDonorRequestDTO.getBloodGroup());
				uniqueRequestTxn2.setPlateletsBags(searchDonorRequestDTO.getPlateletsBags());
				uniqueRequestTxn2.setRequestStatus(RequestStatus.OPEN.toString());
				requestDAO.save(uniqueRequestTxn2);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			uniqueRequestNumber = uniqueRequestTxn.getRequestId();
		}
		return uniqueRequestNumber;
	}
	
	
	public List<UniqueRequestDTO> getRequestList(GetRequestListInputDTO getRequestListInputDTO)
	{
		List<UniqueRequestTxn> uniqueRequestTxnList = requestDAO.getRequestList(getRequestListInputDTO);
		
		Collections.sort(uniqueRequestTxnList, new DateComparator());

		List<UniqueRequestDTO> requestDTOList = RequestMapper.toDTOList(uniqueRequestTxnList);

		return requestDTOList;
	}

	public void closeRequest(UniqueRequestDTO uniqueRequestDTO)
	{
		try
		{
			
			UniqueRequestTxn uniqueRequestTxn = requestDAO.getUniqueRequestTxnByRequestID(uniqueRequestDTO.getRequestNumber());
			uniqueRequestTxn.setRequestStatus(uniqueRequestDTO.getStatus());
			uniqueRequestTxn.setRemarks(uniqueRequestDTO.getCloseRemarks());

			//TODO - act on donors
			
			requestDAO.update(uniqueRequestTxn);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void withdrawRequest(UniqueRequestDTO uniqueRequestDTO)
	{
		try
		{
			
			UniqueRequestTxn uniqueRequestTxn = requestDAO.getUniqueRequestTxnByRequestID(uniqueRequestDTO.getRequestNumber());
			uniqueRequestTxn.setRequestStatus(uniqueRequestDTO.getStatus());
			uniqueRequestTxn.setRemarks(uniqueRequestDTO.getWithdrawRemarks());

			//TODO - act on donors
			
			requestDAO.update(uniqueRequestTxn);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
