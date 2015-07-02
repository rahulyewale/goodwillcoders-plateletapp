package org.ngo.think.dm.common.dto;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetRequestListInputDTO implements Serializable
{

	private static final long serialVersionUID = 1L;

	private String mobileNumber;

	private String requestNumber;

	private String status;

	private Date donationRequestFromDate;
	
	private Date donationRequestToDate;
	
	private Long donationCenterId;
	
	private String patientName;
	
	public String getMobileNumber()
	{
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber)
	{
		this.mobileNumber = mobileNumber;
	}


	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}


	public String getRequestNumber()
	{
		return requestNumber;
	}

	public void setRequestNumber(String requestNumber)
	{
		this.requestNumber = requestNumber;
	}

	public Date getDonationRequestFromDate()
	{
		return donationRequestFromDate;
	}

	public void setDonationRequestFromDate(Date donationRequestFromDate)
	{
		this.donationRequestFromDate = donationRequestFromDate;
	}

	public Date getDonationRequestToDate()
	{
		return donationRequestToDate;
	}

	public void setDonationRequestToDate(Date donationRequestToDate)
	{
		this.donationRequestToDate = donationRequestToDate;
	}

	public Long getDonationCenterId() {
		return donationCenterId;
	}

	public void setDonationCenterId(Long donationCenterId) {
		this.donationCenterId = donationCenterId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}



}
