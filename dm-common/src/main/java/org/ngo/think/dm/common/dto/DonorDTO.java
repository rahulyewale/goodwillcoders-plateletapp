package org.ngo.think.dm.common.dto;

import java.io.Serializable;
import java.util.Date;

public class DonorDTO implements Serializable
{

	private static final long serialVersionUID = 1L;

	private Long donorId;
	private String donorUuid;
	private String firstName;
	private String lastName;
	private String middleName;
	private Date birthDate;
	private String gender;
	private String bloodGroup;
	private String donationComponentType;
	private Integer rating;
	private String createdBy;
	private Date createdDate;
	private String modifiedBy;
	private Date modifiedDate;
	private Date lastDonationDate;
	private Date nextAvailableDate;
	private Integer donationCount;
	private Double distanceKm;
	private boolean selectedDonor;

	public Long getDonorId()
	{
		return donorId;
	}

	public void setDonorId(Long donorId)
	{
		this.donorId = donorId;
	}

	public String getDonorUuid()
	{
		return donorUuid;
	}

	public void setDonorUuid(String donorUuid)
	{
		this.donorUuid = donorUuid;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getMiddleName()
	{
		return middleName;
	}

	public void setMiddleName(String middleName)
	{
		this.middleName = middleName;
	}

	public Date getBirthDate()
	{
		return birthDate;
	}

	public void setBirthDate(Date birthDate)
	{
		this.birthDate = birthDate;
	}

	public String getGender()
	{
		return gender;
	}

	public void setGender(String gender)
	{
		this.gender = gender;
	}

	public String getBloodGroup()
	{
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup)
	{
		this.bloodGroup = bloodGroup;
	}

	public String getDonationComponentType()
	{
		return donationComponentType;
	}

	public void setDonationComponentType(String donationComponentType)
	{
		this.donationComponentType = donationComponentType;
	}

	public Integer getRating()
	{
		return rating;
	}

	public void setRating(Integer rating)
	{
		this.rating = rating;
	}

	public String getCreatedBy()
	{
		return createdBy;
	}

	public void setCreatedBy(String createdBy)
	{
		this.createdBy = createdBy;
	}

	public Date getCreatedDate()
	{
		return createdDate;
	}

	public void setCreatedDate(Date createdDate)
	{
		this.createdDate = createdDate;
	}

	public String getModifiedBy()
	{
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy)
	{
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate()
	{
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate)
	{
		this.modifiedDate = modifiedDate;
	}

	public Date getLastDonationDate()
	{
		return lastDonationDate;
	}

	public void setLastDonationDate(Date lastDonationDate)
	{
		this.lastDonationDate = lastDonationDate;
	}

	public Date getNextAvailableDate()
	{
		return nextAvailableDate;
	}

	public void setNextAvailableDate(Date nextAvailableDate)
	{
		this.nextAvailableDate = nextAvailableDate;
	}

	public Integer getDonationCount()
	{
		return donationCount;
	}

	public void setDonationCount(Integer donationCount)
	{
		this.donationCount = donationCount;
	}

	public Double getDistanceKm()
	{
		return distanceKm;
	}

	public void setDistanceKm(Double distanceKm)
	{
		this.distanceKm = distanceKm;
	}

	public boolean isSelectedDonor()
	{
		return selectedDonor;
	}

	public void setSelectedDonor(boolean selectedDonor)
	{
		this.selectedDonor = selectedDonor;
	}

}
