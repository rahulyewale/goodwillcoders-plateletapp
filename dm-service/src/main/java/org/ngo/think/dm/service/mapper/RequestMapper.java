package org.ngo.think.dm.service.mapper;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.ngo.think.dm.common.dto.DonorDTO;
import org.ngo.think.dm.common.dto.UniqueRequestDTO;
import org.ngo.think.dm.common.util.DateUtil;
import org.ngo.think.dm.persistence.entity.Donor;
import org.ngo.think.dm.persistence.entity.DonorAddressDetail;
import org.ngo.think.dm.persistence.entity.DonorContactDetail;
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
		requestDTO.setUniqueRequestTxnId(uniqueRequestTxn.getUniqueRequestTxnId());
		
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
