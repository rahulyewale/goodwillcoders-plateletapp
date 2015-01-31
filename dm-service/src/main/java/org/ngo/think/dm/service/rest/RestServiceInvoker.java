package org.ngo.think.dm.service.rest;

import org.ngo.think.dm.common.communication.dto.ServiceResponse;
import org.ngo.think.dm.service.domain.LocationResposne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestServiceInvoker
{

	@Autowired
	private RestTemplate restTemplate;
	
	private String restServiceURL;

	public RestTemplate getRestTemplate()
	{
		return restTemplate;
	}

	public void setRestTemplate(RestTemplate restTemplate)
	{
		this.restTemplate = restTemplate;
	}

	public String getRestServiceURL()
	{
		return restServiceURL;
	}

	public void setRestServiceURL(String restServiceURL)
	{
		this.restServiceURL = restServiceURL;
	}

	public ServiceResponse invokeRestService()
	{
		ServiceResponse response = null;
		try
		{
			String origin = "410209";
			String dest = "400710";
			final String url = "http://maps.googleapis.com/maps/api/distancematrix/json?origins={origin}&destinations={destination}&mode=driving&sensor=false&language=en-EN&units=metric";

			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<LocationResposne> responseqwqwq = restTemplate.exchange(url, HttpMethod.GET, null, LocationResposne.class, origin, dest);

			LocationResposne locationResposne = responseqwqwq.getBody();

			System.out.println();
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}

		return response;

	}
}
