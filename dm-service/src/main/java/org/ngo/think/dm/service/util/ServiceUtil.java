package org.ngo.think.dm.service.util;

import org.ngo.think.dm.common.communication.dto.ServiceRequest;
import org.ngo.think.dm.common.util.JsonUtil;

public class ServiceUtil
{

	public static Object extractObjectFromServiceRequest(ServiceRequest serviceRequest, String key, Class clazz)
	{
		Object objectConverted = null;
		try
		{
			String jsonAsString = JsonUtil.convertObjectToJson(serviceRequest.get(key));
			objectConverted = JsonUtil.convertJsonToObject(jsonAsString, clazz);
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		return objectConverted;
	}
}
