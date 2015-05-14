package org.ngo.think.dm.common.enums;

public enum Feature 
{
	SEND_SMS_FEATURE("sendSMSFeature");
	
	private String featureName;
	
	private Feature(String featureName)
	{
		this.featureName = featureName;
	}
	
	public String getFeatureName()
	{
		return this.featureName;
	}
}
