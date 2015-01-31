package org.ngo.think.dm.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the communication_history database table.
 */
@Entity
@Table(name = "communication_history")
@NamedQuery(name = "CommunicationHistory.findAll", query = "SELECT c FROM CommunicationHistory c")
public class CommunicationHistory implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "COMMUNICATION_HISTORY_COMMUNICATIONHISTORYID_GENERATOR", sequenceName = "COMMUNICATION_HISTORY_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMUNICATION_HISTORY_COMMUNICATIONHISTORYID_GENERATOR")
	@Column(name = "communication_history_id")
	private Long communicationHistoryId;

	@Column(name = "created_by")
	private String createdBy;

	@Temporal(TemporalType.DATE)
	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "donor_id")
	private Long donorId;

	@Column(name = "mobile_number")
	private String mobileNumber;

	@Column(name = "request_id")
	private String requestId;

	@Temporal(TemporalType.DATE)
	@Column(name = "requested_date")
	private Date requestedDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "sms_sent_date")
	private Date smsSentDate;

	private String status;

	// bi-directional many-to-one association to DonationCenter
	@ManyToOne
	@JoinColumn(name = "donation_center_id")
	private DonationCenter donationCenter;

	public CommunicationHistory()
	{
	}

	public Long getCommunicationHistoryId()
	{
		return this.communicationHistoryId;
	}

	public void setCommunicationHistoryId(Long communicationHistoryId)
	{
		this.communicationHistoryId = communicationHistoryId;
	}

	public String getCreatedBy()
	{
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy)
	{
		this.createdBy = createdBy;
	}

	public Date getCreatedDate()
	{
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate)
	{
		this.createdDate = createdDate;
	}

	public Long getDonorId()
	{
		return this.donorId;
	}

	public void setDonorId(Long donorId)
	{
		this.donorId = donorId;
	}

	public String getMobileNumber()
	{
		return this.mobileNumber;
	}

	public void setMobileNumber(String mobileNumber)
	{
		this.mobileNumber = mobileNumber;
	}

	public String getRequestId()
	{
		return this.requestId;
	}

	public void setRequestId(String requestId)
	{
		this.requestId = requestId;
	}

	public Date getRequestedDate()
	{
		return this.requestedDate;
	}

	public void setRequestedDate(Date requestedDate)
	{
		this.requestedDate = requestedDate;
	}

	public Date getSmsSentDate()
	{
		return this.smsSentDate;
	}

	public void setSmsSentDate(Date smsSentDate)
	{
		this.smsSentDate = smsSentDate;
	}

	public String getStatus()
	{
		return this.status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public DonationCenter getDonationCenter()
	{
		return this.donationCenter;
	}

	public void setDonationCenter(DonationCenter donationCenter)
	{
		this.donationCenter = donationCenter;
	}

}