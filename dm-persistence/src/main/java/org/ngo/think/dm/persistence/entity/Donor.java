package org.ngo.think.dm.persistence.entity;

import java.io.Serializable;

import javax.persistence.*;

import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * The persistent class for the donor database table.
 */
@Entity
@NamedQuery(name = "Donor.findAll", query = "SELECT d FROM Donor d")
public class Donor implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "DONOR_DONORID_GENERATOR", sequenceName = "DONOR_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DONOR_DONORID_GENERATOR")
	@Column(name = "donor_id")
	private Long donorId;

	@Temporal(TemporalType.DATE)
	@Column(name = "birth_date")
	private Date birthDate;

	@Column(name = "blood_group")
	private String bloodGroup;

	@Column(name = "created_by")
	private String createdBy;

	@Temporal(TemporalType.DATE)
	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "donation_component_type")
	private String donationComponentType;

	@Column(name = "donor_uuid")
	private String donorUuid;

	@Column(name = "first_name")
	private String firstName;

	private String gender;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "middle_name")
	private String middleName;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Temporal(TemporalType.DATE)
	@Column(name = "modified_date")
	private Date modifiedDate;

	// bi-directional many-to-one association to DonationHistory
	@OneToMany(mappedBy = "donor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<DonationHistory> donationHistories;

	// bi-directional many-to-one association to DonorAddressDetail
	@OneToMany(mappedBy = "donor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<DonorAddressDetail> donorAddressDetails;

	// bi-directional many-to-one association to DonorContactDetail
	@OneToMany(mappedBy = "donor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<DonorContactDetail> donorContactDetails;
	
	@Transient
	private String distanceInKm;
	
	@Transient
	private Integer rating;
	
	@Transient
	private Date nextAvailableDate;
	
	@Transient
	private Date lastDonationDate;
	
	@Transient
	private Integer distanceInMeter;

	public Integer getDistanceInMeter()
	{
		return distanceInMeter;
	}

	public Donor()
	{
	}

	public Long getDonorId()
	{
		return this.donorId;
	}

	public void setDonorId(Long donorId)
	{
		this.donorId = donorId;
	}

	public Date getBirthDate()
	{
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate)
	{
		this.birthDate = birthDate;
	}

	public String getBloodGroup()
	{
		return this.bloodGroup;
	}

	public void setBloodGroup(String bloodGroup)
	{
		this.bloodGroup = bloodGroup;
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

	public String getDonationComponentType()
	{
		return this.donationComponentType;
	}

	public void setDonationComponentType(String donationComponentType)
	{
		this.donationComponentType = donationComponentType;
	}

	public String getDonorUuid()
	{
		return this.donorUuid;
	}

	public void setDonorUuid(String donorUuid)
	{
		this.donorUuid = donorUuid;
	}

	public String getFirstName()
	{
		return this.firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getGender()
	{
		return this.gender;
	}

	public void setGender(String gender)
	{
		this.gender = gender;
	}

	public String getLastName()
	{
		return this.lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getMiddleName()
	{
		return this.middleName;
	}

	public void setMiddleName(String middleName)
	{
		this.middleName = middleName;
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

	public List<DonationHistory> getDonationHistories()
	{
		return this.donationHistories;
	}

	public void setDonationHistories(List<DonationHistory> donationHistories)
	{
		this.donationHistories = donationHistories;
	}

	public DonationHistory addDonationHistory(DonationHistory donationHistory)
	{
		getDonationHistories().add(donationHistory);
		donationHistory.setDonor(this);

		return donationHistory;
	}

	public DonationHistory removeDonationHistory(DonationHistory donationHistory)
	{
		getDonationHistories().remove(donationHistory);
		donationHistory.setDonor(null);

		return donationHistory;
	}

	public List<DonorAddressDetail> getDonorAddressDetails()
	{
		return this.donorAddressDetails;
	}

	public void setDonorAddressDetails(List<DonorAddressDetail> donorAddressDetails)
	{
		this.donorAddressDetails = donorAddressDetails;
	}

	public DonorAddressDetail addDonorAddressDetail(DonorAddressDetail donorAddressDetail)
	{
		getDonorAddressDetails().add(donorAddressDetail);
		donorAddressDetail.setDonor(this);

		return donorAddressDetail;
	}

	public DonorAddressDetail removeDonorAddressDetail(DonorAddressDetail donorAddressDetail)
	{
		getDonorAddressDetails().remove(donorAddressDetail);
		donorAddressDetail.setDonor(null);

		return donorAddressDetail;
	}

	public List<DonorContactDetail> getDonorContactDetails()
	{
		return this.donorContactDetails;
	}

	public void setDonorContactDetails(List<DonorContactDetail> donorContactDetails)
	{
		this.donorContactDetails = donorContactDetails;
	}

	public DonorContactDetail addDonorContactDetail(DonorContactDetail donorContactDetail)
	{
		getDonorContactDetails().add(donorContactDetail);
		donorContactDetail.setDonor(this);

		return donorContactDetail;
	}

	public DonorContactDetail removeDonorContactDetail(DonorContactDetail donorContactDetail)
	{
		getDonorContactDetails().remove(donorContactDetail);
		donorContactDetail.setDonor(null);

		return donorContactDetail;
	}

	public String getDistanceInKm()
	{
		return distanceInKm;
	}

	public void setDistanceInKm(String distanceInKm)
	{
		this.distanceInKm = distanceInKm;
	}

	public Integer getRating()
	{
		return rating;
	}

	public void setRating(Integer rating)
	{
		this.rating = rating;
	}

	public Date getNextAvailableDate()
	{
		return nextAvailableDate;
	}

	public void setNextAvailableDate(Date nextAvailableDate)
	{
		this.nextAvailableDate = nextAvailableDate;
	}

	public Date getLastDonationDate()
	{
		return lastDonationDate;
	}

	public void setLastDonationDate(Date lastDonationDate)
	{
		this.lastDonationDate = lastDonationDate;
	}

	public void setDistanceInMeter(Integer distanceInMeter)
	{
		this.distanceInMeter = distanceInMeter;
		
	}

}