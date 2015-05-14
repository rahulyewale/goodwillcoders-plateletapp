package org.ngo.think.dm.service.controller;

import static org.ngo.think.dm.common.constant.CommonConstants.CommonAttributes.APPLICATION_JSON;
import static org.ngo.think.dm.common.constant.CommonConstants.ServiceRequestMapping.SEND_SMS;

import org.ngo.think.dm.common.communication.dto.ResponseData;
import org.ngo.think.dm.common.communication.dto.ServiceRequest;
import org.ngo.think.dm.common.communication.dto.ServiceResponse;
import org.ngo.think.dm.common.constant.CommonConstants;
import org.ngo.think.dm.common.dto.DonorAppointmentDTO;
import org.ngo.think.dm.service.CommunicationHistoryService;
import org.ngo.think.dm.service.ff4j.FeatureProvider;
import org.ngo.think.dm.service.util.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CommunicationHistoryServiceController
{
	@Autowired
	private CommunicationHistoryService communicationHistoryService;

	@Autowired
	private FeatureProvider featureProvider;

	@RequestMapping(value = SEND_SMS, method = RequestMethod.POST, consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
	@ResponseBody
	public ServiceResponse sendSMSForSearchedDonors(@RequestBody
	ServiceRequest serviceRequest)
	{
		DonorAppointmentDTO donorAppointmentDTO = (DonorAppointmentDTO) ServiceUtil.extractObjectFromServiceRequest(serviceRequest, CommonConstants.RequestKey.SEND_SMS_REQUEST, DonorAppointmentDTO.class);

		ResponseData responseData = communicationHistoryService.sendSMSToDonors(donorAppointmentDTO);
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
