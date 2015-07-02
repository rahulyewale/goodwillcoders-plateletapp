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
 * The persistent class for the unique_request_txn database table.
 */
@Entity
@Table(name = "unique_request_txn")
@NamedQueries({
					@NamedQuery(name = "UniqueRequestTxn.findAll", query = "SELECT u FROM UniqueRequestTxn u"),
								@NamedQuery(name = "UniqueRequestTxn.getUniqueRequestTxnByDatePatientNameAndBloodGroup", query = "SELECT u FROM UniqueRequestTxn u WHERE u.requestStatus = 'OPEN' AND u.patientName = :patientName AND u.requestDate = :requestDate AND u.bloodGroup = :bloodGroup"),
               @NamedQuery(name = "UniqueRequestTxn.getUniqueRequestTxnByID", query = "SELECT u FROM UniqueRequestTxn u WHERE u.requestId =:requestId")
					})

public class UniqueRequestTxn implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "UNIQUE_REQUEST_TXN_UNIQUEREQUESTTXNID_GENERATOR", sequenceName = "UNIQUE_REQUEST_TXN_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UNIQUE_REQUEST_TXN_UNIQUEREQUESTTXNID_GENERATOR")
	@Column(name = "unique_request_txn_id")
	private Long uniqueRequestTxnId;

	@ManyToOne
	@JoinColumn(name="donation_center_id")
	private DonationCenter donationCenter;

	@Temporal(TemporalType.DATE)
	@Column(name = "request_date")
	private Date requestDate;

	@Column(name = "request_id")
	private String requestId;
	
	@Column(name = "request_status")
	private String requestStatus;
	
	@Column(name = "remarks")
	private String remarks;
	
	@Column(name = "blood_group")
	private String bloodGroup;

	@Column(name = "platelets_bags")
	private int plateletsBags;
	
	@Column(name = "patient_name")
	private String patientName;

	public Long getUniqueRequestTxnId()
	{
		return this.uniqueRequestTxnId;
	}

	public void setUniqueRequestTxnId(Long uniqueRequestTxnId)
	{
		this.uniqueRequestTxnId = uniqueRequestTxnId;
	}


	public DonationCenter getDonationCenter()
	{
		return donationCenter;
	}

	public void setDonationCenter(DonationCenter donationCenter)
	{
		this.donationCenter = donationCenter;
	}

	public Date getRequestDate()
	{
		return this.requestDate;
	}

	public void setRequestDate(Date requestDate)
	{
		this.requestDate = requestDate;
	}

	public String getRequestId()
	{
		return this.requestId;
	}

	public void setRequestId(String requestId)
	{
		this.requestId = requestId;
	}

	public String getRequestStatus()
	{
		return requestStatus;
	}

	public void setRequestStatus(String requestStatus)
	{
		this.requestStatus = requestStatus;
	}

	public String getRemarks()
	{
		return remarks;
	}

	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
	}

	public String getBloodGroup()
	{
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup)
	{
		this.bloodGroup = bloodGroup;
	}

	public int getPlateletsBags()
	{
		return plateletsBags;
	}

	public void setPlateletsBags(int plateletsBags)
	{
		this.plateletsBags = plateletsBags;
	}
	
	public String getPatientName()
	{
		return patientName;
	}

	public void setPatientName(String patientName)
	{
		this.patientName = patientName;
	}
	

}