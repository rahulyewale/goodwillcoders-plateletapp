package org.ngo.think.dm.service.controller;

import static org.ngo.think.dm.common.constant.CommonConstants.CommonAttributes.APPLICATION_JSON;
import static org.ngo.think.dm.common.constant.CommonConstants.ServiceRequestMapping.ADD_DONATION_HISTORY;

import org.ngo.think.dm.common.communication.dto.ResponseData;
import org.ngo.think.dm.common.communication.dto.ServiceRequest;
import org.ngo.think.dm.common.communication.dto.ServiceResponse;
import org.ngo.think.dm.common.constant.CommonConstants;
import org.ngo.think.dm.common.dto.DonationHistoryDTO;
import org.ngo.think.dm.service.DonationHistoryService;
import org.ngo.think.dm.service.util.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DonationHistoryServiceController
{
	@Autowired
	private DonationHistoryService donationHistoryService;

	@RequestMapping(value = ADD_DONATION_HISTORY, method = RequestMethod.POST, consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
	@ResponseBody
	public ServiceResponse addDonationHistory(@RequestBody
	ServiceRequest serviceRequest)
	{
		DonationHistoryDTO donationHistoryDTO = (DonationHistoryDTO) ServiceUtil.extractObjectFromServiceRequest(serviceRequest, CommonConstants.RequestKey.ADD_DONATION_HISTORY, DonationHistoryDTO.class);

		ResponseData responseData = donationHistoryService.addDonationHistory(donationHistoryDTO);
		ServiceResponse response = new ServiceResponse(responseData);
		return response;

	}

	/*
	 * @RequestMapping(value="/sendSMSForConfirmedDonors",method=RequestMethod.POST
	 * ,consumes=APPLICATION_JSON,produces=APPLICATION_JSON)
	 * @ResponseBody public ServiceResponse
	 * sendConfirmedSMSForSearchedDonors(@RequestBody ServiceRequest
	 * serviceRequest) { return null; }
	 */
}
