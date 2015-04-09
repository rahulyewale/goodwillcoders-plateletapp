package org.ngo.think.dm.service.controller;

import java.util.List;

import org.ngo.think.dm.common.communication.dto.ResponseData;
import org.ngo.think.dm.common.communication.dto.ServiceRequest;
import org.ngo.think.dm.common.communication.dto.ServiceResponse;
import org.ngo.think.dm.common.constant.CommonConstants;
import org.ngo.think.dm.common.dto.GetRequestListInputDTO;
import org.ngo.think.dm.common.dto.GetRequestListResponse;
import org.ngo.think.dm.common.dto.UniqueRequestDTO;
import org.ngo.think.dm.common.enums.ResponseCategory;
import org.ngo.think.dm.common.enums.ResponseType;
import org.ngo.think.dm.service.UniqueRequestTransactionService;
import org.ngo.think.dm.service.util.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RequestServiceController
{

	@Autowired
	private UniqueRequestTransactionService requestTransactionService;
	
	@RequestMapping(value="/getrequestlist",method=RequestMethod.POST,consumes="application/json",produces="application/json")
	@ResponseBody
	public ServiceResponse getRequestList(@RequestBody ServiceRequest serviceRequest)
	{
		
		GetRequestListInputDTO getRequestListInputDTO = (GetRequestListInputDTO) ServiceUtil.extractObjectFromServiceRequest(serviceRequest, CommonConstants.RequestKey.GET_REQUEST_LIST_REQUEST, GetRequestListInputDTO.class);
		
		List<UniqueRequestDTO> requestDTOList =  requestTransactionService.getRequestList(getRequestListInputDTO);
		
		
		GetRequestListResponse getRequestListResponse =  new GetRequestListResponse();
		getRequestListResponse.setRequestListOutputList(requestDTOList);
		
		ServiceResponse serviceResponse = new ServiceResponse(new ResponseData(ResponseType.SUCCESS, ResponseCategory.SUCCESS));
		
		serviceResponse.add(CommonConstants.ResponseKey.GET_REQUEST_LIST_RESPONSE, getRequestListResponse);
		
		return serviceResponse;
	}
	
}
