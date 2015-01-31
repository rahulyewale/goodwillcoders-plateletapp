package org.ngo.think.dm.common.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class DonorDTO implements Serializable

{
	private boolean selectedDonor;
	private Long donorId;
	private String donorUuid;
	private String firstName;
	private String middleName;
	private String lastName;
	private Date birthDate;
	
	private String gender;
	private String bloodGroup;
	
	private String donationComponentType;
	private int rating;
	
	private String createdBy;
	private Date createdDate;
	private String modifiedBy;
	private Date modifiedDate;
	
	
	private DonorContactDetailsDTO  donorContactDetailsDTO;
	private DonorAddressDetailsDTO donorAddressDetailsDTO;
	
	private Date lastDonationDate;
	private Date nextAvailableDate;
	
	private ArrayList <DonationHistoryDTO> donationHistoryList = new ArrayList<DonationHistoryDTO>();
	private Integer donationCount;
	private Double distanceKm;
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public String getDonationComponentType() {
		return donationComponentType;
	}
	public void setDonationComponentType(String donationComponentType) {
		this.donationComponentType = donationComponentType;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public Long getDonorId() {
		return donorId;
	}
	public void setDonorId(Long donorId) {
		this.donorId = donorId;
	}
	public String getDonorUuid() {
		return donorUuid;
	}
	public void setDonorUuid(String donorUuid) {
		this.donorUuid = donorUuid;
	}
	public DonorContactDetailsDTO getDonorContactDetailsDTO() {
		return donorContactDetailsDTO;
	}
	public void setDonorContactDetailsDTO(
			DonorContactDetailsDTO donorContactDetailsDTO) {
		this.donorContactDetailsDTO = donorContactDetailsDTO;
	}
	public DonorAddressDetailsDTO getDonorAddressDetailsDTO() {
		return donorAddressDetailsDTO;
	}
	public void setDonorAddressDetailsDTO(
			DonorAddressDetailsDTO donorAddressDetailsDTO) {
		this.donorAddressDetailsDTO = donorAddressDetailsDTO;
	}
	public ArrayList<DonationHistoryDTO> getDonationHistoryList() {
		return donationHistoryList;
	}
	public void setDonationHistoryList(
			ArrayList<DonationHistoryDTO> donationHistoryList) {
		this.donationHistoryList = donationHistoryList;
	}
	public boolean isSelectedDonor() {
		return selectedDonor;
	}
	public void setSelectedDonor(boolean selectedDonor) {
		this.selectedDonor = selectedDonor;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public Date getLastDonationDate() {
		return lastDonationDate;
	}
	public void setLastDonationDate(Date lastDonationDate) {
		this.lastDonationDate = lastDonationDate;
	}
	public Date getNextAvailableDate() {
		return nextAvailableDate;
	}
	public void setNextAvailableDate(Date nextAvailableDate) {
		this.nextAvailableDate = nextAvailableDate;
	}
	public Integer getDonationCount() {
		return donationCount;
	}
	public void setDonationCount(Integer donationCount) {
		this.donationCount = donationCount;
	}
	public Double getDistanceKm() {
		return distanceKm;
	}
	public void setDistanceKm(Double distanceKm) {
		this.distanceKm = distanceKm;
	}
	
	
}
