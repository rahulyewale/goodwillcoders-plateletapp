package org.ngo.think.dm.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the donation_center database table.
 */
@Entity
@Table(name = "donation_center")
@NamedQuery(name = "DonationCenter.findAll", query = "SELECT d FROM DonationCenter d")
public class DonationCenter implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "DONATION_CENTER_DONATIONCENTERID_GENERATOR", sequenceName = "DONATION_CENTER_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DONATION_CENTER_DONATIONCENTERID_GENERATOR")
	@Column(name = "donation_center_id")
	private Long donationCenterId;

	@Column(name = "donation_center_address")
	private String addressLine1;

	@Column(name = "location")
	private String city;

	@Column(name = "created_by")
	private String createdBy;

	@Temporal(TemporalType.DATE)
	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "donation_center_name")
	private String donationCenterName;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Temporal(TemporalType.DATE)
	@Column(name = "modified_date")
	private Date modifiedDate;

	@Column(name = "pin_code")
	private String pinCode;

	private String state;
	
	@Column(name = "primary_telephone_number")
	private String primaryTelephoneNo;
	
	@Column(name = "secondary_telephone_number")
	private String secondaryTelephoneNo;
	
	@Column(name = "fax_number")
	private String faxNumber;
	
	
	@Transient
	private String centerAddress;
	
	@Transient
	private double longitude;
	
	@Transient
	private double lattitude;

	// bi-directional many-to-one association to CommunicationHistory
	@OneToMany(mappedBy = "donationCenter")
	private List<CommunicationHistory> communicationHistories;

	public DonationCenter()
	{
	}

	public Long getDonationCenterId()
	{
		return this.donationCenterId;
	}

	public void setDonationCenterId(Long donationCenterId)
	{
		this.donationCenterId = donationCenterId;
	}

	public String getAddressLine1()
	{
		return this.addressLine1;
	}

	public void setAddressLine1(String addressLine1)
	{
		this.addressLine1 = addressLine1;
	}

	public String getCity()
	{
		return this.city;
	}

	public void setCity(String city)
	{
		this.city = city;
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

	public String getDonationCenterName()
	{
		return this.donationCenterName;
	}

	public void setDonationCenterName(String donationCenterName)
	{
		this.donationCenterName = donationCenterName;
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

	public String getPinCode()
	{
		return this.pinCode;
	}

	public void setPinCode(String pinCode)
	{
		this.pinCode = pinCode;
	}

	public String getState()
	{
		return this.state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public List<CommunicationHistory> getCommunicationHistories()
	{
		return this.communicationHistories;
	}

	public void setCommunicationHistories(List<CommunicationHistory> communicationHistories)
	{
		this.communicationHistories = communicationHistories;
	}

	public CommunicationHistory addCommunicationHistory(CommunicationHistory communicationHistory)
	{
		getCommunicationHistories().add(communicationHistory);
		communicationHistory.setDonationCenter(this);

		return communicationHistory;
	}

	public CommunicationHistory removeCommunicationHistory(CommunicationHistory communicationHistory)
	{
		getCommunicationHistories().remove(communicationHistory);
		communicationHistory.setDonationCenter(null);

		return communicationHistory;
	}

	public String getCenterAddress()
	{
		return centerAddress;
	}

	public void setCenterAddress(String centerAddress)
	{
		this.centerAddress = centerAddress;
	}

	public double getLongitude()
	{
		return longitude;
	}

	public void setLongitude(double longitude)
	{
		this.longitude = longitude;
	}

	public double getLattitude()
	{
		return lattitude;
	}

	public void setLattitude(double lattitude)
	{
		this.lattitude = lattitude;
	}

	
	
	

}