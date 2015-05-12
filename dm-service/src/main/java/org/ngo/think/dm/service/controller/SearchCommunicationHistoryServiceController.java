package org.ngo.think.dm.service.controller;

import org.ngo.think.dm.common.communication.dto.ResponseData;
import org.ngo.think.dm.common.communication.dto.ServiceRequest;
import org.ngo.think.dm.common.communication.dto.ServiceResponse;
import org.ngo.think.dm.common.constant.CommonConstants;
import org.ngo.think.dm.common.dto.SearchCommunicationHistoryRequestDTO;
import org.ngo.think.dm.common.dto.SearchCommunicationHistoryResponseDTO;
import org.ngo.think.dm.common.enums.ResponseCategory;
import org.ngo.think.dm.common.enums.ResponseType;
import org.ngo.think.dm.service.SearchCommunicationHistoryService;
import org.ngo.think.dm.service.util.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import static org.ngo.think.dm.common.constant.CommonConstants.RequestMapping.*;
@Controller
public class SearchCommunicationHistoryServiceController
{

	@Autowired
	SearchCommunicationHistoryService searchCommunicationHistoryService;
	
	
	@RequestMapping(value=COMMUNICATION_HISTORY_SEARCH_SERVICE,method=RequestMethod.POST,consumes="application/json",produces="application/json")
	@ResponseBody
	public ServiceResponse searchcommunicationhistory(@RequestBody ServiceRequest serviceRequest)
	{
		System.out.println("In SearchCommunicationHistoryServiceController : searchcommunicationhistory !!!");
		SearchCommunicationHistoryRequestDTO  searchCommunicationHistoryRequest = (SearchCommunicationHistoryRequestDTO) ServiceUtil.extractObjectFromServiceRequest(serviceRequest,CommonConstants.RequestKey.SEARCH_COMMUNICATION_HISTORY_REQUEST, SearchCommunicationHistoryRequestDTO.class);
		SearchCommunicationHistoryResponseDTO searchCommunicationHistoryResponse = searchCommunicationHistoryService.searchcommunicationhistory(searchCommunicationHistoryRequest);
		
		ResponseData responseData =  new ResponseData(ResponseType.SUCCESS,ResponseCategory.SUCCESS);
		ServiceResponse serviceResponse = new ServiceResponse(responseData, CommonConstants.ResponseKey.SEARCH_COMMUNICATION_HISTORY_RESPONSE, searchCommunicationHistoryResponse);
		return serviceResponse;
		
		
		
	}
}
