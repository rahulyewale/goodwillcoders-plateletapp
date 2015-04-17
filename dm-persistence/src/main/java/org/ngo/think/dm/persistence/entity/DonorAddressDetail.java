package org.ngo.think.dm.persistence.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;

/**
 * The persistent class for the donor_address_details database table.
 */
@Entity
@Table(name = "donor_address_details")
@NamedQuery(name = "DonorAddressDetail.findAll", query = "SELECT d FROM DonorAddressDetail d")
public class DonorAddressDetail implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "DONOR_ADDRESS_DETAILS_DONORADDRESSID_GENERATOR", sequenceName = "DONOR_ADDRESS_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DONOR_ADDRESS_DETAILS_DONORADDRESSID_GENERATOR")
	@Column(name = "donor_address_id")
	private Long donorAddressId;

	@Column(name = "address_line_1")
	private String addressLine1;

	@Column(name = "address_line_2")
	private String addressLine2;

	private String city;

	@Column(name = "created_by")
	private String createdBy;

	@Temporal(TemporalType.DATE)
	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Temporal(TemporalType.DATE)
	@Column(name = "modified_date")
	private Date modifiedDate;

	@Column(name = "pin_code")
	private String pinCode;

	private String state;
	
	@Transient
	private double longitude;
	
	@Transient
	private double lattitude;

	// bi-directional many-to-one association to Donor
	@ManyToOne
	@JoinColumn(name = "donor_id")
	private Donor donor;

	public DonorAddressDetail()
	{
	}

	public Long getDonorAddressId()
	{
		return this.donorAddressId;
	}

	public void setDonorAddressId(Long donorAddressId)
	{
		this.donorAddressId = donorAddressId;
	}

	public String getAddressLine1()
	{
		return this.addressLine1;
	}

	public void setAddressLine1(String addressLine1)
	{
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2()
	{
		return this.addressLine2;
	}

	public void setAddressLine2(String addressLine2)
	{
		this.addressLine2 = addressLine2;
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

	public Donor getDonor()
	{
		return this.donor;
	}

	public void setDonor(Donor donor)
	{
		this.donor = donor;
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