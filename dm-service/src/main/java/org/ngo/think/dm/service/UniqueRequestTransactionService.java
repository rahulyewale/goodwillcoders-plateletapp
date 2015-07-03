package org.ngo.think.dm.service;

import java.util.Collections;
import java.util.List;

import org.ngo.think.dm.common.constant.RequestStatus;
import org.ngo.think.dm.common.dto.GetRequestListInputDTO;
import org.ngo.think.dm.common.dto.SearchDonorRequestDTO;
import org.ngo.think.dm.common.dto.UniqueRequestDTO;
import org.ngo.think.dm.common.enums.RandomNumberType;
import org.ngo.think.dm.common.util.RandomNumberGenerator;
import org.ngo.think.dm.persistence.dao.CommunicationHistoryDAO;
import org.ngo.think.dm.persistence.dao.UniqueRequestDAO;
import org.ngo.think.dm.persistence.entity.CommunicationHistory;
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

	@Autowired
	CommunicationHistoryDAO communicationHistoryDAO;

	public String getUniqueRequestTranactionID(SearchDonorRequestDTO searchDonorRequestDTO) throws Exception
	{
		String uniqueRequestNumber = null;
		UniqueRequestTxn uniqueRequestTxn = requestDAO.getUniqueRequestTxnByDatePatientNameAndBloodGroup(searchDonorRequestDTO.getRequestDate(), searchDonorRequestDTO.getPatientname(), searchDonorRequestDTO.getBloodGroup());

		
		
		if (null == uniqueRequestTxn)
		{
			uniqueRequestNumber = RandomNumberGenerator.generateRandomNumber(RandomNumberType.REQUEST_NUMBER);
			try
			{
				System.out.println("patient name at random req = "+searchDonorRequestDTO.getPatientname());
				
				UniqueRequestTxn uniqueRequestTxn2 = new UniqueRequestTxn();
				DonationCenter donationCenter = new DonationCenter();
				donationCenter.setDonationCenterId(searchDonorRequestDTO.getDonationCentre());
				uniqueRequestTxn2.setDonationCenter(donationCenter);
				uniqueRequestTxn2.setRequestDate(searchDonorRequestDTO.getRequestDate());
				uniqueRequestTxn2.setRequestId(uniqueRequestNumber);
				uniqueRequestTxn2.setBloodGroup(searchDonorRequestDTO.getBloodGroup());
				uniqueRequestTxn2.setPlateletsBags(searchDonorRequestDTO.getPlateletsBags());
				uniqueRequestTxn2.setRequestStatus(RequestStatus.OPEN.toString());
				
				//for patient name
				uniqueRequestTxn2.setPatientName(searchDonorRequestDTO.getPatientname());
				
				requestDAO.save(uniqueRequestTxn2);
			}
			catch (Exception e)
			{
				throw e;
			}
		}
		else
		{
			uniqueRequestNumber = uniqueRequestTxn.getRequestId();
			uniqueRequestTxn.setPlateletsBags(searchDonorRequestDTO.getPlateletsBags());
			

			///FOR CHANGING DONATION CENTER ON NON UNIQUE REQUEST
			DonationCenter donationCenter = new DonationCenter();
			donationCenter.setDonationCenterId(searchDonorRequestDTO.getDonationCentre());
			uniqueRequestTxn.setDonationCenter(donationCenter);

			try
			{
				requestDAO.update(uniqueRequestTxn);
			}
			catch (Exception e)
			{
				throw e;
			}
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

	public void closeRequest(UniqueRequestDTO uniqueRequestDTO) throws InsufficientDonationException, Exception
	{
		UniqueRequestTxn uniqueRequestTxn = requestDAO.getUniqueRequestTxnByRequestID(uniqueRequestDTO.getRequestNumber());

		if (!RequestStatus.OPEN.toString().equals(uniqueRequestTxn.getRequestStatus()))
		{
			// TODO throw exception... as request is NOT OPEN
			throw new RequestClosureException("Sorry, only 'Open' requests can be closed.");
		}

		List<CommunicationHistory> communicationHistoryList = communicationHistoryDAO.getDonatedStateCommunicationHistoryByRequest(uniqueRequestTxn.getRequestId());
		if (uniqueRequestTxn.getPlateletsBags() <= communicationHistoryList.size())
		{
			uniqueRequestTxn.setRequestStatus(RequestStatus.CLOSED.toString());
			uniqueRequestTxn.setRemarks(uniqueRequestDTO.getCloseRemarks());
			requestDAO.update(uniqueRequestTxn);
		}
		else
		{
			throw new InsufficientDonationException("Insufficient platelets donation. Request can not be closed.");
		}

		// TODO - act on donors

	}

	public void withdrawRequest(UniqueRequestDTO uniqueRequestDTO)
	{
		try
		{

			UniqueRequestTxn uniqueRequestTxn = requestDAO.getUniqueRequestTxnByRequestID(uniqueRequestDTO.getRequestNumber());
			uniqueRequestTxn.setRequestStatus(RequestStatus.WITHDRAWN.toString());
			uniqueRequestTxn.setRemarks(uniqueRequestDTO.getWithdrawRemarks());

			// TODO - act on donors

			requestDAO.update(uniqueRequestTxn);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
