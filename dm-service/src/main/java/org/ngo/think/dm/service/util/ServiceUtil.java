package org.ngo.think.dm.service.util;

import java.util.Set;

import org.ngo.think.dm.common.communication.dto.ServiceRequest;
import org.ngo.think.dm.common.util.JsonUtil;
import org.ngo.think.dm.persistence.entity.PostalCodeMaster;

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
	
	public static void handleUniquePostalCodes(Set<PostalCodeMaster> uniquePostalCodeMasters, PostalCodeMaster postalCodeMaster)
	{
		boolean successfullyAdded = uniquePostalCodeMasters.add(postalCodeMaster);
		if(!successfullyAdded)
		{
			addOffsetToCoordinates(postalCodeMaster);
		   
		   uniquePostalCodeMasters.add(postalCodeMaster);
		}
	}

	public static void addOffsetToCoordinates(PostalCodeMaster postalCodeMaster)
	{
		double newLattitude = postalCodeMaster.getLattitude() + (Math.random() -.5) / 1500;
		double newLongitude = postalCodeMaster.getLongitude() + (Math.random() -.5) / 1500;
		postalCodeMaster.setLattitude(newLattitude);
		postalCodeMaster.setLongitude(newLongitude);
	}
}
