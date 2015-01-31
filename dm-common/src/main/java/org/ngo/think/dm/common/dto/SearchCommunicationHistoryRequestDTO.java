package org.ngo.think.dm.common.dto;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchCommunicationHistoryRequestDTO implements Serializable
{

	private static final long serialVersionUID = 1L;

	private String mobileNumber;

	private String requestTxnId;

	private String status;

	private Date donationRequestDate;

	public String getMobileNumber()
	{
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber)
	{
		this.mobileNumber = mobileNumber;
	}

	public String getRequestTxnId()
	{
		return requestTxnId;
	}

	public void setRequestTxnId(String requestTxnId)
	{
		this.requestTxnId = requestTxnId;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public Date getDonationRequestDate()
	{
		return donationRequestDate;
	}

	public void setDonationRequestDate(Date donationRequestDate)
	{
		this.donationRequestDate = donationRequestDate;
	}

}
