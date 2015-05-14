package org.ngo.think.dm.service.controller;

import static org.ngo.think.dm.common.constant.CommonConstants.CommonAttributes.APPLICATION_JSON;
import static org.ngo.think.dm.common.constant.CommonConstants.ServiceRequestMapping.SEARCH_DONOR;

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
	private SearchDonorService searchDonorService;

	@RequestMapping(value = SEARCH_DONOR, method = RequestMethod.POST, consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
	@ResponseBody
	public ServiceResponse searchDonor(@RequestBody
	ServiceRequest serviceRequest) throws Exception
	{
		//System.out.println(JsonUtil.convertObjectToJson(serviceRequest));
		SearchDonorRequestDTO donorRequestDTO = (SearchDonorRequestDTO) ServiceUtil.extractObjectFromServiceRequest(serviceRequest, CommonConstants.RequestKey.SEARCH_DONOR_REQUEST, SearchDonorRequestDTO.class);

		SearchDonorResponseDTO searchDonorResponseDTO = searchDonorService.searchDonor(donorRequestDTO);

		ServiceResponse serviceResponse = new ServiceResponse(new ResponseData(ResponseType.SUCCESS, ResponseCategory.SUCCESS));

		serviceResponse.add(CommonConstants.ResponseKey.SEARCH_DONOR_RESPONSE, searchDonorResponseDTO);

		return serviceResponse;
	}

}
