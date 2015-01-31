package org.ngo.think.dm.common.communication.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonIgnoreType;
import org.ngo.think.dm.common.Context.ContextInfo;
import org.ngo.think.dm.common.util.JsonUtil;

@JsonIgnoreType
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceRequest implements Serializable
{

	private static final long serialVersionUID = 1L;

	private ContextInfo contextInfo;

	private Map<String, Object> requestObjects;

	private ServiceRequest()
	{
		this.setRequestObjects(new HashMap<String, Object>());
	}

	public ServiceRequest(ContextInfo contextInfo)
	{
		this();
		this.setContextInfo(contextInfo);
	}

	public ServiceRequest(ContextInfo contextInfo, String key, Object object)
	{
		this(contextInfo);
		this.getRequestObjects().put(key, object);
	}

	public ContextInfo getContextInfo()
	{
		return contextInfo;
	}

	private void setContextInfo(ContextInfo contextInfo)
	{
		this.contextInfo = contextInfo;
	}

	public Map<String, Object> getRequestObjects()
	{
		return requestObjects;
	}

	private void setRequestObjects(Map<String, Object> requestObjects)
	{
		this.requestObjects = requestObjects;
	}

	public void add(String key, Object object)
	{
		this.getRequestObjects().put(key, object);
	}

	public Object get(String key)
	{
		return this.getRequestObjects().get(key);
	}

	@SuppressWarnings("unchecked")
	public <T> T get(String key, Class<T> pojoClass)
	{
		if (this.getRequestObjects().get(key) == null)
		{
			return null;
		}

		String jsonString = null;
		try
		{
			jsonString = JsonUtil.convertObjectToJson(this.getRequestObjects().get(key));
		}
		catch (Exception ioException)
		{
		}
		if (StringUtils.isNotEmpty(jsonString))
		{
			try
			{
				return (T) JsonUtil.convertJsonToObject(jsonString, pojoClass);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return null;
	}

}