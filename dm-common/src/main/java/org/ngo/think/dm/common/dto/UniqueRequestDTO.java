package org.ngo.think.dm.common.dto;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UniqueRequestDTO implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String requestNumber;
	private String requestedDate;
	private String bloodGroup;
	private String status;
	private String remarks;
	private Long uniqueRequestTxnId;
	private DonationCenterDTO donationCenterDTO;
	private Date donationDate;

	public String getRequestNumber()
	{
		return requestNumber;
	}

	public void setRequestNumber(String requestNumber)
	{
		this.requestNumber = requestNumber;
	}


	public String getRequestedDate()
	{
		return requestedDate;
	}

	public void setRequestedDate(String requestedDate)
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

	public String getRemarks()
	{
		return remarks;
	}

	public void setRemarks(String remarks)
	{
		System.out.println("setting remarks:" + this + " " + remarks);
		this.remarks = remarks;
	}

	public DonationCenterDTO getDonationCenterDTO()
	{
		return donationCenterDTO;
	}

	public void setDonationCenterDTO(DonationCenterDTO donationCenterDTO)
	{
		this.donationCenterDTO = donationCenterDTO;
	}


	public Long getUniqueRequestTxnId()
	{
		return uniqueRequestTxnId;
	}

	public void setUniqueRequestTxnId(Long uniqueRequestTxnId)
	{
		this.uniqueRequestTxnId = uniqueRequestTxnId;
	}

	public String getBloodGroup()
	{
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup)
	{
		this.bloodGroup = bloodGroup;
	}

	public Date getDonationDate()
	{
		return donationDate;
	}

	public void setDonationDate(Date donationDate)
	{
		this.donationDate = donationDate;
	}

	

}
