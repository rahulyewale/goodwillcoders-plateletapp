package org.ngo.think.dm.service.controller;

import org.ngo.think.dm.common.communication.dto.ServiceRequest;
import org.ngo.think.dm.common.communication.dto.ServiceResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CommunicationHistoryServiceController
{

	@RequestMapping(value="/sendSMSForSearchedDonors",method=RequestMethod.POST,consumes="application/json",produces="application/json")
	@ResponseBody
	public ServiceResponse sendSMSForSearchedDonors(@RequestBody ServiceRequest serviceRequest)
	{
		
		return null;
		
	}
	
	@RequestMapping(value="/sendSMSForSearchedDonors",method=RequestMethod.POST,consumes="application/json",produces="application/json")
	@ResponseBody
	public ServiceResponse sendConfirmedSMSForSearchedDonors(@RequestBody ServiceRequest serviceRequest)
	{
		
		return null;
		
	}
}
