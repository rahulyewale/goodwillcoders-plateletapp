package org.ngo.think.dm.common.dto;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DonorAppointmentDTO implements Serializable

{

	private static final long serialVersionUID = 1L;

	private List<DonorDTO> donors;

	private Long centerId;

	private String initialSMS;

	private String confirmSMS;

	private String requestTxnId;

	public List<DonorDTO> getDonors()
	{
		return donors;
	}

	public void setDonors(List<DonorDTO> donors)
	{
		this.donors = donors;
	}

	public Long getCenterId()
	{
		return centerId;
	}

	public void setCenterId(Long centerId)
	{
		this.centerId = centerId;
	}

	public String getInitialSMS()
	{
		return initialSMS;
	}

	public void setInitialSMS(String initialSMS)
	{
		this.initialSMS = initialSMS;
	}

	public String getConfirmSMS()
	{
		return confirmSMS;
	}

	public void setConfirmSMS(String confirmSMS)
	{
		this.confirmSMS = confirmSMS;
	}

	public String getRequestTxnId()
	{
		return requestTxnId;
	}

	public void setRequestTxnId(String requestTxnId)
	{
		this.requestTxnId = requestTxnId;
	}

	/*
	 * private Long donorId; private String status; private String contactNumber;
	 * private Date requestedDate; private Long donationCenterId; private String
	 * donationCenterAddressForSms; private String uniqueRequestTxnId;
	 */

}
