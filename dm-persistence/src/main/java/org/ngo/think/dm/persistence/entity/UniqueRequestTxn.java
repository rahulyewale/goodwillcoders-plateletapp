package org.ngo.think.dm.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the unique_request_txn database table.
 */
@Entity
@Table(name = "unique_request_txn")
@NamedQuery(name = "UniqueRequestTxn.findAll", query = "SELECT u FROM UniqueRequestTxn u")
public class UniqueRequestTxn implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "UNIQUE_REQUEST_TXN_UNIQUEREQUESTTXNID_GENERATOR", sequenceName = "UNIQUE_REQUEST_TXN_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UNIQUE_REQUEST_TXN_UNIQUEREQUESTTXNID_GENERATOR")
	@Column(name = "unique_request_txn_id")
	private Long uniqueRequestTxnId;

	@Column(name = "donation_center_id")
	private Long donationCenterId;

	@Temporal(TemporalType.DATE)
	@Column(name = "request_date")
	private Date requestDate;

	@Column(name = "request_id")
	private String requestId;

	public UniqueRequestTxn()
	{
	}

	public Long getUniqueRequestTxnId()
	{
		return this.uniqueRequestTxnId;
	}

	public void setUniqueRequestTxnId(Long uniqueRequestTxnId)
	{
		this.uniqueRequestTxnId = uniqueRequestTxnId;
	}

	public Long getDonationCenterId()
	{
		return this.donationCenterId;
	}

	public void setDonationCenterId(Long donationCenterId)
	{
		this.donationCenterId = donationCenterId;
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

}