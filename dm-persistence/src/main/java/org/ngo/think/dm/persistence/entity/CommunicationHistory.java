package org.ngo.think.dm.persistence.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the communication_history database table.
 */
@Entity
@Table(name = "communication_history")
@NamedQueries({
	@NamedQuery(name = "CommunicationHistory.findAll", query = "SELECT c FROM CommunicationHistory c"),
	@NamedQuery(name = "CommunicationHistory.getCommunicationHistoryOfConfirmedDonor", query = "SELECT u FROM CommunicationHistory u WHERE u.status ='CONFIRMED' AND u.donorId =:donorId AND u.requestedDate <= :requestedDate"),
								@NamedQuery(name = "CommunicationHistory.getCommunicationHistoryOfDonorOnOrAfterRequirementDate", query = "SELECT u FROM CommunicationHistory u WHERE u.donorId =:donorId AND u.requestedDate >= :today"),
	@NamedQuery(name = "CommunicationHistory.getCommunicationHistoryForSMSCheck", query = "SELECT u FROM CommunicationHistory u WHERE u.requestId =:requestId AND u.donorId =:donorId AND u.requestedDate =:requestedDate AND u.donationCenter.donationCenterId =:donationCenterId"),
	@NamedQuery(name = "CommunicationHistory.getCommunicationHistoryForScreen", query = "SELECT u FROM CommunicationHistory u"),
	@NamedQuery(name = "CommunicationHistory.getDonatedStateCommunicationHistoryOfRequest", query = "SELECT u FROM CommunicationHistory u WHERE u.status =:status AND u.requestId =:requestId")
})
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