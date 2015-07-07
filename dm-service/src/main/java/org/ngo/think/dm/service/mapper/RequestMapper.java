package org.ngo.think.dm.service.mapper;

import java.util.ArrayList;
import java.util.List;

import org.ngo.think.dm.common.dto.UniqueRequestDTO;
import org.ngo.think.dm.common.util.DateUtil;
import org.ngo.think.dm.persistence.entity.UniqueRequestTxn;

public class RequestMapper
{

	public static UniqueRequestDTO toDTO(UniqueRequestTxn uniqueRequestTxn)
	{
		UniqueRequestDTO requestDTO = new UniqueRequestDTO();
		
		requestDTO.setDonationCenterDTO(DonationCenterMapper.toDTO(uniqueRequestTxn.getDonationCenter()));
		
		requestDTO.setRequestedDate(DateUtil.dateToString(uniqueRequestTxn.getRequestDate()));
		
		requestDTO.setRequestNumber(uniqueRequestTxn.getRequestId());
		requestDTO.setStatus(uniqueRequestTxn.getRequestStatus());
		requestDTO.setCloseRemarks(uniqueRequestTxn.getRemarks());
		requestDTO.setWithdrawRemarks(uniqueRequestTxn.getRemarks());
		requestDTO.setUniqueRequestTxnId(uniqueRequestTxn.getUniqueRequestTxnId());
		requestDTO.setBloodGroup(uniqueRequestTxn.getBloodGroup());
		requestDTO.setPlateletsBags(uniqueRequestTxn.getPlateletsBags());
		///for counts
		requestDTO.setConfirmedCount(uniqueRequestTxn.getConfirmedCount());
		requestDTO.setDonatedCount(uniqueRequestTxn.getDonatedCount());
		requestDTO.setRejectCount(uniqueRequestTxn.getRejectCount());
		requestDTO.setReserveCount(uniqueRequestTxn.getReserveCount());
		
		requestDTO.setPatientName(uniqueRequestTxn.getPatientName());
		
		return requestDTO;
		
		
	}
	public static List<UniqueRequestDTO> toDTOList(List<UniqueRequestTxn> requestTxnList)
	{
		List<UniqueRequestDTO> requestDTOList = new ArrayList<UniqueRequestDTO>();
		
		for (UniqueRequestTxn uniqueRequestTxn : requestTxnList)
		{
			UniqueRequestDTO requestDTO = toDTO(uniqueRequestTxn);
			requestDTOList.add(requestDTO);
		}
		return requestDTOList;
	}
	
	

}
