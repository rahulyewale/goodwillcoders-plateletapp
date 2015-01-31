package org.ngo.think.dm.common.Context;

import java.io.Serializable;

public class ContextInfo implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	private String actionedBy;

	public String getActionedBy()
	{
		return actionedBy;
	}

	public void setActionedBy(String actionedBy)
	{
		this.actionedBy = actionedBy;
	}
}
