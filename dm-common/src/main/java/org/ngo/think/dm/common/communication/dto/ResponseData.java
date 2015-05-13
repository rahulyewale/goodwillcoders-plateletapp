package org.ngo.think.dm.common.communication.dto;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.ngo.think.dm.common.enums.ResponseCategory;
import org.ngo.think.dm.common.enums.ResponseSeverity;
import org.ngo.think.dm.common.enums.ResponseType;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseData implements Serializable
{

	private static final long serialVersionUID = 1L;

	private ResponseType responseType;

	private ResponseSeverity responseSeverity;

	private ResponseCategory responseCategory;

	private String message;

	public static ResponseData successResponseData = new ResponseData(ResponseType.SUCCESS, ResponseCategory.SUCCESS);

	public static ResponseData errorResponseData = new ResponseData(ResponseType.ERROR, ResponseCategory.ERROR);

	private ResponseData()
	{
	}

	public ResponseData(ResponseType responseType, ResponseCategory responseCategory, ResponseSeverity responseSeverity, String message)
	{
		this();
		this.setResponseType(responseType);
		this.setResponseSeverity(responseSeverity);
		this.setResponseCategory(responseCategory);
		this.setMessage(message);
	}

	public ResponseData(ResponseType responseType, ResponseCategory responseCategory)
	{
		this(responseType, responseCategory, null, null);
	}

	public ResponseType getResponseType()
	{
		return responseType;
	}

	public void setResponseType(ResponseType responseType)
	{
		this.responseType = responseType;
	}

	public ResponseSeverity getResponseSeverity()
	{
		return responseSeverity;
	}

	public void setResponseSeverity(ResponseSeverity responseSeverity)
	{
		this.responseSeverity = responseSeverity;
	}

	public ResponseCategory getResponseCategory()
	{
		return responseCategory;
	}

	public void setResponseCategory(ResponseCategory responseCategory)
	{
		this.responseCategory = responseCategory;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((responseType == null) ? 0 : responseType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (getClass() != obj.getClass())
		{
			return false;
		}
		ResponseData otherData = (ResponseData) obj;
		if (responseType != otherData.responseType)
		{
			return false;
		}
		return true;
	}
}
