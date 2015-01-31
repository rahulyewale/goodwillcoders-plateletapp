package org.ngo.think.dm.common.dto;

import java.io.Serializable;
import java.util.Date;

public class DonorAppointmentDTO implements Serializable

{

	private static final long serialVersionUID = 1L;

	private Long donorId;
	private String status;
	private String contactNumber;

	private Date requestedDate;
	private Long donationCenterId;
	private String donationCenterAddressForSms;
	private String uniqueRequestTxnId;
	public Long getDonorId()
	{
		return donorId;
	}
	public void setDonorId(Long donorId)
	{
		this.donorId = donorId;
	}
	public String getStatus()
	{
		return status;
	}
	public void setStatus(String status)
	{
		this.status = status;
	}
	public String getContactNumber()
	{
		return contactNumber;
	}
	public void setContactNumber(String contactNumber)
	{
		this.contactNumber = contactNumber;
	}
	public Date getRequestedDate()
	{
		return requestedDate;
	}
	public void setRequestedDate(Date requestedDate)
	{
		this.requestedDate = requestedDate;
	}
	public Long getDonationCenterId()
	{
		return donationCenterId;
	}
	public void setDonationCenterId(Long donationCenterId)
	{
		this.donationCenterId = donationCenterId;
	}
	public String getDonationCenterAddressForSms()
	{
		return donationCenterAddressForSms;
	}
	public void setDonationCenterAddressForSms(String donationCenterAddressForSms)
	{
		this.donationCenterAddressForSms = donationCenterAddressForSms;
	}
	public String getUniqueRequestTxnId()
	{
		return uniqueRequestTxnId;
	}
	public void setUniqueRequestTxnId(String uniqueRequestTxnId)
	{
		this.uniqueRequestTxnId = uniqueRequestTxnId;
	}
	
	
}
