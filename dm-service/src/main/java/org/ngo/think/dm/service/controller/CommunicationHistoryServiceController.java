package org.ngo.think.dm.service.controller;

import org.ngo.think.dm.common.communication.dto.ServiceRequest;
import org.ngo.think.dm.common.communication.dto.ServiceResponse;
import org.ngo.think.dm.common.dto.DonorAppointmentDTO;
import org.ngo.think.dm.common.util.JsonUtil;
import org.ngo.think.dm.service.CommunicationHistoryService;
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

	@RequestMapping(value="/sendSMSForSearchedDonors",method=RequestMethod.POST,consumes="application/json",produces="application/json")
	@ResponseBody
	public ServiceResponse sendSMSForSearchedDonors(@RequestBody ServiceRequest serviceRequest)
	{
		DonorAppointmentDTO donorAppointmentDTO = (DonorAppointmentDTO)ServiceUtil.extractObjectFromServiceRequest(serviceRequest, "donorAppointmentDTO", DonorAppointmentDTO.class);
		
		communicationHistoryService.sendSMSForSearchedDonors(donorAppointmentDTO);
		return null;
		
	}
	
	@RequestMapping(value="/sendSMSForSearchedDonors",method=RequestMethod.POST,consumes="application/json",produces="application/json")
	@ResponseBody
	public ServiceResponse sendConfirmedSMSForSearchedDonors(@RequestBody ServiceRequest serviceRequest)
	{
		
		return null;
		
	}
}
