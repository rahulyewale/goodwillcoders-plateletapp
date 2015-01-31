package org.ngo.think.dm.web.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.ngo.think.dm.common.communication.dto.ServiceRequest;
import org.ngo.think.dm.common.communication.dto.ServiceResponse;

import org.ngo.think.dm.common.util.JsonUtil;

public class RestSeviceInvoker
{
	public static ServiceResponse invokeRestService(String serviceUrl,ServiceRequest serviceRequest) throws Exception
	{
		ServiceResponse serviceResponse = null;

		StringBuffer bufferOp = new StringBuffer();
		try
		{

			DefaultHttpClient httpClient = new DefaultHttpClient();

			HttpPost postRequest = new HttpPost(serviceUrl);

			StringEntity input = new StringEntity(JsonUtil.convertObjectToJson(serviceRequest));
			input.setContentType("application/json");
			postRequest.setEntity(input);

			HttpResponse response = httpClient.execute(postRequest);

			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

			String output = null;
			while ((output = br.readLine()) != null)
			{
				bufferOp.append(output);
			}

			httpClient.getConnectionManager().shutdown();

		}
		catch (MalformedURLException e)
		{

			e.printStackTrace();

		}
		catch (IOException e)
		{

			e.printStackTrace();

		}

		serviceResponse = (ServiceResponse) JsonUtil.convertJsonToObject(bufferOp.toString(), ServiceResponse.class);

		return serviceResponse;

	}

}
