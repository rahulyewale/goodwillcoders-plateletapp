package org.ngo.think.dm.service.controller;

import static org.ngo.think.dm.common.constant.CommonConstants.CommonAttributes.APPLICATION_JSON;
import static org.ngo.think.dm.common.constant.CommonConstants.ServiceRequestMapping.GET_FEATURES;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.ff4j.core.Feature;
import org.ngo.think.dm.common.communication.dto.ResponseData;
import org.ngo.think.dm.common.communication.dto.ServiceRequest;
import org.ngo.think.dm.common.communication.dto.ServiceResponse;
import org.ngo.think.dm.common.constant.CommonConstants;
import org.ngo.think.dm.common.enums.ResponseCategory;
import org.ngo.think.dm.common.enums.ResponseType;
import org.ngo.think.dm.service.ff4j.FeatureProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FeatureServiceController
{
	@Autowired
	private FeatureProvider featureProvider;

	@RequestMapping(value = GET_FEATURES, method = RequestMethod.POST, consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
	@ResponseBody
	public ServiceResponse getSystemFeatures(@RequestBody
	ServiceRequest serviceRequest)
	{
		Map<String, Boolean> featureMap = new HashMap<String, Boolean>();
		Map<String, Feature> fetchedFeatureMap = featureProvider.getFF4j().getFeatures();

		Collection<Feature> values = fetchedFeatureMap.values();

		Iterator<Feature> itr = values.iterator();
		while (itr.hasNext())
		{
			Feature feature = itr.next();
			featureMap.put(feature.getUid(), feature.isEnable());
		}

		ResponseData responseData = new ResponseData(ResponseType.SUCCESS, ResponseCategory.SUCCESS);
		ServiceResponse serviceResponse = new ServiceResponse(responseData, CommonConstants.ResponseKey.SYSTEM_FEATURES, featureMap);
		return serviceResponse;

	}
}
