package org.ngo.think.dm.service.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.ff4j.core.Feature;
import org.ngo.think.dm.common.communication.dto.ResponseData;
import org.ngo.think.dm.common.communication.dto.ServiceRequest;
import org.ngo.think.dm.common.communication.dto.ServiceResponse;
import org.ngo.think.dm.common.constant.CommonConstants;
import org.ngo.think.dm.common.dto.DonorAppointmentDTO;
import org.ngo.think.dm.common.enums.ResponseCategory;
import org.ngo.think.dm.common.enums.ResponseType;
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
	
	@RequestMapping(value="/sendSMSToDonors",method=RequestMethod.POST,consumes="application/json",produces="application/json")
	@ResponseBody
	public ServiceResponse sendSMSForSearchedDonors(@RequestBody ServiceRequest serviceRequest)
	{
		DonorAppointmentDTO donorAppointmentDTO = (DonorAppointmentDTO)ServiceUtil.extractObjectFromServiceRequest(serviceRequest, CommonConstants.RequestKey.SEND_SMS_REQUEST, DonorAppointmentDTO.class);
		
		ResponseData responseData = communicationHistoryService.sendSMSToDonors(donorAppointmentDTO);
		ServiceResponse response = new ServiceResponse(responseData);
		return response;
		
	}
	
	//move to separate controller
	@RequestMapping(value="getFeatures",method=RequestMethod.POST,consumes="application/json",produces="application/json")
	@ResponseBody
	public ServiceResponse searchcommunicationhistory(@RequestBody ServiceRequest serviceRequest)
	{
		Map<String, Boolean> featureMap = new HashMap<String, Boolean>();
		Map<String, org.ff4j.core.Feature> fetchedFeatureMap = featureProvider.getFF4j().getFeatures();
		
		Collection<Feature> values = fetchedFeatureMap.values();

		Iterator itr = values.iterator();
		while (itr.hasNext())
		{
			Feature feature = (Feature) itr.next();
			featureMap.put(feature.getUid(), feature.isEnable());
		}
		
		ResponseData responseData =  new ResponseData(ResponseType.SUCCESS,ResponseCategory.SUCCESS);
		ServiceResponse serviceResponse = new ServiceResponse(responseData, CommonConstants.ResponseKey.SYSTEM_FEATURES, featureMap);
		return serviceResponse;
		
		
		
	}
	/*@RequestMapping(value="/sendSMSForConfirmedDonors",method=RequestMethod.POST,consumes="application/json",produces="application/json")
	@ResponseBody
	public ServiceResponse sendConfirmedSMSForSearchedDonors(@RequestBody ServiceRequest serviceRequest)
	{
		
		return null;
		
	}*/
}
