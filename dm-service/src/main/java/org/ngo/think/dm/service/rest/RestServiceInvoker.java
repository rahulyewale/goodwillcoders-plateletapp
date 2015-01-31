package org.ngo.think.dm.service.rest;

import org.ngo.think.dm.common.communication.dto.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
		return null;

	}
}
