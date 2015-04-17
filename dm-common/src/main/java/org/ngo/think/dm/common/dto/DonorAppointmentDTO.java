package org.ngo.think.dm.common.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.ngo.think.dm.common.constant.CommonConstants.HistoryStatus;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DonorAppointmentDTO implements Serializable

{

	private static final long serialVersionUID = 1L;

	private List<DonorDTO> donors;

	private Long centerId;
	
	private Date requestedDate;

	private String initialSMS;

	private String confirmSMS;

	private String requestTxnId;
	
	private String status;

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

	public Date getRequestedDate()
	{
		return requestedDate;
	}

	public void setRequestedDate(Date requestedDate)
	{
		this.requestedDate = requestedDate;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	public String getSMSText(String status)
	{
		String smsText = null;
		if (HistoryStatus.SMS_SENT.equals(status))
		{
			smsText = getInitialSMS();
		}
		else if (HistoryStatus.CONFIRMED.equals(status))
		{
			smsText = getConfirmSMS();
		}
		return smsText;
	}
	
	/*
	 * private Long donorId; private String status; private String contactNumber;
	 * private Date requestedDate; private Long donationCenterId; private String
	 * donationCenterAddressForSms; private String uniqueRequestTxnId;
	 */

}
