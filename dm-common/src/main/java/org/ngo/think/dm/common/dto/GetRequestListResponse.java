package org.ngo.think.dm.common.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetRequestListResponse implements Serializable
{
	private static final long serialVersionUID = 1L;

	private List<UniqueRequestDTO> requestListOutputList = new ArrayList<UniqueRequestDTO>();

	public List<UniqueRequestDTO> getRequestListOutputList()
	{
		return requestListOutputList;
	}

	public void setRequestListOutputList(List<UniqueRequestDTO> requestListOutputList)
	{
		this.requestListOutputList = requestListOutputList;
	}

}
