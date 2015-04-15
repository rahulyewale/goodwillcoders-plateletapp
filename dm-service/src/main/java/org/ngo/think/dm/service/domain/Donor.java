package org.ngo.think.dm.service.domain;

import java.util.Date;

public class Donor
{

	private Long donorId;

	private String firstName;

	private String lastName;

	private String dateOfBirth;
	
	private String bloodGroup;

	private String residentialAddress;

	private String pincode;

	private String city;

	private String state;

	private String contactNumber;

	private String email;

	private int donationFrequency;

	public Long getDonorId() {
		return donorId;
	}

	public void setDonorId(Long donorId) {
		this.donorId = donorId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getResidentialAddress() {
		return residentialAddress;
	}

	public void setResidentialAddress(String residentialAddress) {
		this.residentialAddress = residentialAddress;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getDonationFrequency() {
		return donationFrequency;
	}

	public void setDonationFrequency(int donationFrequency) {
		this.donationFrequency = donationFrequency;
	}

	@Override
	public String toString() {
		return "Donor [donorId=" + donorId + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth
				+ ", residentialAddress=" + residentialAddress + ", pincode="
				+ pincode + ", city=" + city + ", state=" + state
				+ ", contactNumber=" + contactNumber + ", email=" + email
				+ ", donationFrequency=" + donationFrequency + "]";
	}


}
