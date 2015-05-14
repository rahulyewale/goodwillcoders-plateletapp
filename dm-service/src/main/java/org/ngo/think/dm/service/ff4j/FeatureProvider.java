package org.ngo.think.dm.service.ff4j;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.ff4j.FF4j;
import org.ff4j.core.Feature;
import org.ff4j.web.api.FF4JProvider;
import org.ngo.think.dm.common.communication.dto.ResponseData;
import org.ngo.think.dm.common.communication.dto.ServiceRequest;
import org.ngo.think.dm.common.communication.dto.ServiceResponse;
import org.ngo.think.dm.common.constant.CommonConstants;
import org.ngo.think.dm.common.dto.SearchCommunicationHistoryRequestDTO;
import org.ngo.think.dm.common.dto.SearchCommunicationHistoryResponseDTO;
import org.ngo.think.dm.common.enums.ResponseCategory;
import org.ngo.think.dm.common.enums.ResponseType;
import org.ngo.think.dm.service.util.ServiceUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
public class FeatureProvider implements FF4JProvider
{

	private final FF4j ff4j;
	
	public FeatureProvider()
	{
		this.ff4j = new FF4j("ff4j.xml");
	}
	
	@Override
	public FF4j getFF4j()
	{
		return ff4j;
	}
	

}
