package org.ngo.think.dm.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the donation_history database table.
 */
@Entity
@Table(name = "donation_history")
@NamedQuery(name = "DonationHistory.findAll", query = "SELECT d FROM DonationHistory d")
public class DonationHistory implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "DONATION_HISTORY_DONATIONHISTORYID_GENERATOR", sequenceName = "DONATION_HISTORY_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DONATION_HISTORY_DONATIONHISTORYID_GENERATOR")
	@Column(name = "donation_history_id")
	private Long donationHistoryId;

	@Column(name = "created_by")
	private String createdBy;

	@Temporal(TemporalType.DATE)
	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "donated_component_type")
	private String donatedComponentType;

	@Temporal(TemporalType.DATE)
	@Column(name = "donation_date")
	private Date donationDate;

	private boolean isrejected;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Temporal(TemporalType.DATE)
	@Column(name = "modified_date")
	private Date modifiedDate;

	private String remarks;

	// bi-directional many-to-one association to Donor
	@ManyToOne
	@JoinColumn(name = "donor_id")
	private Donor donor;

	public DonationHistory()
	{
	}

	public Long getDonationHistoryId()
	{
		return this.donationHistoryId;
	}

	public void setDonationHistoryId(Long donationHistoryId)
	{
		this.donationHistoryId = donationHistoryId;
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

	public String getDonatedComponentType()
	{
		return this.donatedComponentType;
	}

	public void setDonatedComponentType(String donatedComponentType)
	{
		this.donatedComponentType = donatedComponentType;
	}

	public Date getDonationDate()
	{
		return this.donationDate;
	}

	public void setDonationDate(Date donationDate)
	{
		this.donationDate = donationDate;
	}

	public boolean getIsrejected()
	{
		return this.isrejected;
	}

	public void setIsrejected(boolean isrejected)
	{
		this.isrejected = isrejected;
	}

	public String getModifiedBy()
	{
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy)
	{
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate()
	{
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate)
	{
		this.modifiedDate = modifiedDate;
	}

	public String getRemarks()
	{
		return this.remarks;
	}

	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
	}

	public Donor getDonor()
	{
		return this.donor;
	}

	public void setDonor(Donor donor)
	{
		this.donor = donor;
	}

}