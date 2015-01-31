package org.ngo.think.dm.service.controller;

import org.ngo.think.dm.common.communication.dto.ResponseData;
import org.ngo.think.dm.common.communication.dto.ServiceRequest;
import org.ngo.think.dm.common.communication.dto.ServiceResponse;
import org.ngo.think.dm.common.constant.CommonConstants;
import org.ngo.think.dm.common.dto.SearchDonorRequestDTO;
import org.ngo.think.dm.common.dto.SearchDonorResponseDTO;
import org.ngo.think.dm.common.enums.ResponseCategory;
import org.ngo.think.dm.common.enums.ResponseType;
import org.ngo.think.dm.service.SearchDonorService;
import org.ngo.think.dm.service.util.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SearchDonorServiceController
{

	@Autowired
	SearchDonorService searchDonorService;
	
	@RequestMapping(value="searchdonor",method=RequestMethod.POST,consumes="application/json",produces="application/json")
	@ResponseBody
	public ServiceResponse searchDonor(@RequestBody ServiceRequest serviceRequest)
	{
		SearchDonorRequestDTO donorRequestDTO = (SearchDonorRequestDTO) ServiceUtil.extractObjectFromServiceRequest(serviceRequest, CommonConstants.RequestKey.SEARCH_DONOR_REQUEST, SearchDonorRequestDTO.class);
		
		SearchDonorResponseDTO searchDonorResponseDTO =  searchDonorService.searchDonor(donorRequestDTO);
		
		ServiceResponse serviceResponse = new ServiceResponse(new ResponseData(ResponseType.SUCCESS, ResponseCategory.SUCCESS));
		
		serviceResponse.add(CommonConstants.ResponseKey.SEARCH_DONOR_RESPONSE, searchDonorResponseDTO);
		
		return serviceResponse;
	}
	
}
