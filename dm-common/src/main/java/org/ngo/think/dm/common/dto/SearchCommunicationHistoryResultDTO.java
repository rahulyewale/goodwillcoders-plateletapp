package org.ngo.think.dm.common.dto;

import java.io.Serializable;
import java.util.Date;

public class SearchCommunicationHistoryResultDTO implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String requestId;
	private Long donorId;
	private String donorUuid;
	private String donorName;
	private String mobileNumber;
	private Date requestedDate;
	private String status;
	private Date smsSentDate;

	public String getRequestId()
	{
		return requestId;
	}

	public void setRequestId(String requestId)
	{
		this.requestId = requestId;
	}

	public Long getDonorId()
	{
		return donorId;
	}

	public void setDonorId(Long donorId)
	{
		this.donorId = donorId;
	}

	public String getDonorUuid()
	{
		return donorUuid;
	}

	public void setDonorUuid(String donorUuid)
	{
		this.donorUuid = donorUuid;
	}

	public String getDonorName()
	{
		return donorName;
	}

	public void setDonorName(String donorName)
	{
		this.donorName = donorName;
	}

	public String getMobileNumber()
	{
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber)
	{
		this.mobileNumber = mobileNumber;
	}

	public Date getRequestedDate()
	{
		return requestedDate;
	}

	public void setRequestedDate(Date requestedDate)
	{
		this.requestedDate = requestedDate;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public Date getSmsSentDate()
	{
		return smsSentDate;
	}

	public void setSmsSentDate(Date smsSentDate)
	{
		this.smsSentDate = smsSentDate;
	}

}
