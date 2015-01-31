package org.ngo.think.dm.common.communication.dto;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.ngo.think.dm.common.util.JsonUtil;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceResponse implements Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The response data. */
	private ResponseData responseData;

	/** The response objects. */
	private Map<String, Object> responseObjects;

	/**
	 * Instantiates a new service response.
	 */
	private ServiceResponse()
	{
		this.setResponseObjects(new HashMap<String, Object>());
	}

	/**
	 * Instantiates a new service response.
	 * @param responseData the response data
	 */
	public ServiceResponse(ResponseData responseData)
	{
		this();
		this.setResponseData(responseData);
	}

	/**
	 * Instantiates a new service response.
	 * @param responseData the response data
	 * @param key the key
	 * @param object the object
	 */
	public ServiceResponse(ResponseData responseData, String key, Object object)
	{
		this(responseData);
		this.getResponseObjects().put(key, object);
	}

	/**
	 * Gets the response data.
	 * @return the response data
	 */
	public ResponseData getResponseData()
	{
		return responseData;
	}

	/**
	 * Sets the response data.
	 * @param responseData the new response data
	 */
	private void setResponseData(ResponseData responseData)
	{
		this.responseData = responseData;
	}

	/**
	 * Gets the response objects.
	 * @return the response objects
	 */
	public Map<String, Object> getResponseObjects()
	{
		return responseObjects;
	}

	/**
	 * Sets the response objects.
	 * @param responseObjects the response objects
	 */
	private void setResponseObjects(Map<String, Object> responseObjects)
	{
		this.responseObjects = responseObjects;
	}

	/**
	 * Adds the.
	 * @param key the key
	 * @param object the object
	 */
	public void add(String key, Object object)
	{
		this.getResponseObjects().put(key, object);
	}

	/**
	 * Gets the object.
	 * @param key the key
	 * @return the object
	 */
	public Object get(String key)
	{
		return this.getResponseObjects().get(key);
	}

	/**
	 * This method is used get the serviceResponse Object which is stored in the
	 * map by name and type you are expecting. It performs first getting the
	 * object's JSON string and converting that JSON string to object.
	 * @param <T> the generic type
	 * @param key the key
	 * @param pojoClass the pojo class
	 * @return the object
	 */
	@SuppressWarnings("unchecked")
	public <T> T get(String key, Class<T> pojoClass)
	{
		// Checking if key and value present
		if (this.getResponseObjects().get(key) == null)
		{
			return null;
		}

		String jsonString = null;
		try
		{
			// Creating the JSONString
			jsonString = JsonUtil.convertObjectToJson(this.getResponseObjects().get(key));
		}
		catch (Exception ioException)
		{
		}
		// Creating the object from the JSON String
		if (StringUtils.isNotEmpty(jsonString))
		{
			try
			{
				return (T) JsonUtil.convertJsonToObject(jsonString, pojoClass);
			}
			catch (Exception e)
			{
			}
		}
		return null;
	}

	@Override
	public String toString()
	{
		return this.getClass().getSimpleName() + "[responseData=" + this.responseData + ", responseObjects=" + this.responseObjects + "]";
	}

}

