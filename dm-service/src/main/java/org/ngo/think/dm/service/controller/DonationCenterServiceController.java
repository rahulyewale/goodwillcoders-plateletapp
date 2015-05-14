package org.ngo.think.dm.service.controller;

import static org.ngo.think.dm.common.constant.CommonConstants.CommonAttributes.APPLICATION_JSON;
import static org.ngo.think.dm.common.constant.CommonConstants.ServiceRequestMapping.GET_DONATION_CENTERS;

import org.ngo.think.dm.common.Context.ContextInfo;
import org.ngo.think.dm.common.communication.dto.ResponseData;
import org.ngo.think.dm.common.communication.dto.ServiceRequest;
import org.ngo.think.dm.common.communication.dto.ServiceResponse;
import org.ngo.think.dm.common.constant.CommonConstants;
import org.ngo.think.dm.common.dto.GetDonationCenterResponseDTO;
import org.ngo.think.dm.common.enums.ResponseCategory;
import org.ngo.think.dm.common.enums.ResponseType;
import org.ngo.think.dm.common.util.JsonUtil;
import org.ngo.think.dm.service.DonationCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DonationCenterServiceController
{

	@Autowired
	private DonationCenterService donationCenterService;

	@RequestMapping(value = GET_DONATION_CENTERS, method = RequestMethod.POST, consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
	@ResponseBody
	public ServiceResponse getDonationCenters(@RequestBody
	ServiceRequest serviceRequest)
	{

		GetDonationCenterResponseDTO getDonationCenterResponseDTO = donationCenterService.getDonationCenters();

		ServiceResponse serviceResponse = new ServiceResponse(new ResponseData(ResponseType.SUCCESS, ResponseCategory.SUCCESS));

		serviceResponse.add(CommonConstants.ResponseKey.SEARCH_DOATION_CENTER_RESPONSE, getDonationCenterResponseDTO);

		return serviceResponse;
	}

	public static void main(String[] args) throws Exception
	{
		ServiceRequest request = new ServiceRequest(new ContextInfo());

		System.out.println(JsonUtil.convertObjectToJson(request));
	}
}
