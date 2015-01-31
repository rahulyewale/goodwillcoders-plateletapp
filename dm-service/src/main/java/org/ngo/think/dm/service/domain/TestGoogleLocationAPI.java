package org.ngo.think.dm.service.domain;

import org.ngo.think.dm.service.rest.RestServiceInvoker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class TestGoogleLocationAPI 
{

	@Autowired
	private RestServiceInvoker restServiceInvoker;
	
	@RequestMapping(value="/testGoogle", method=RequestMethod.GET)
	@ResponseBody
	public String callGoogle()
	{
		restServiceInvoker.invokeRestService();
		
		System.out.println("sadgqwbga");
		return null;
	}

}
