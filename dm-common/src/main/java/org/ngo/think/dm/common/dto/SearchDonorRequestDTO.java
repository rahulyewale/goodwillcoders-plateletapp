package org.ngo.think.dm.common.dto;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchDonorRequestDTO implements Serializable
{

	private static final long serialVersionUID = 1L;
	private String donationType;
	
	private String patientname;

	private String bloodGroup;

	private Date requestDate = new Date();

	private Long donationCentre;

	private int plateletsBags;

	private Integer numberOfDonationsLessThanCount;

	private Integer notDonatedInLastMonthsCount;

	private boolean searchAvailableDonorsOnly;
	
	private String centerAddress;

	public String getDonationType()
	{
		return donationType;
	}

	public void setDonationType(String donationType)
	{
		this.donationType = donationType;
	}

	public String getBloodGroup()
	{
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup)
	{
		this.bloodGroup = bloodGroup;
	}

	public Date getRequestDate()
	{
		return requestDate;
	}

	public void setRequestDate(Date requestDate)
	{
		this.requestDate = requestDate;
	}

	public Long getDonationCentre()
	{
		return donationCentre;
	}

	public void setDonationCentre(Long donationCentre)
	{
		this.donationCentre = donationCentre;
	}

	public int getPlateletsBags()
	{
		return plateletsBags;
	}

	public void setPlateletsBags(int plateletsBags)
	{
		this.plateletsBags = plateletsBags;
	}

	public Integer getNumberOfDonationsLessThanCount()
	{
		return numberOfDonationsLessThanCount;
	}

	public void setNumberOfDonationsLessThanCount(Integer numberOfDonationsLessThanCount)
	{
		this.numberOfDonationsLessThanCount = numberOfDonationsLessThanCount;
	}

	public Integer getNotDonatedInLastMonthsCount()
	{
		return notDonatedInLastMonthsCount;
	}

	public void setNotDonatedInLastMonthsCount(Integer notDonatedInLastMonthsCount)
	{
		this.notDonatedInLastMonthsCount = notDonatedInLastMonthsCount;
	}

	public boolean getSearchAvailableDonorsOnly()
	{
		return searchAvailableDonorsOnly;
	}

	public void setSearchAvailableDonorsOnly(boolean searchAvailableDonorsOnly)
	{
		this.searchAvailableDonorsOnly = searchAvailableDonorsOnly;
	}

	public String getCenterAddress()
	{
		return centerAddress;
	}

	public void setCenterAddress(String centerAddress)
	{
		this.centerAddress = centerAddress;
	}

	public String getPatientname() {
		return patientname;
	}

	public void setPatientname(String patientname) {
		this.patientname = patientname;
	}

}
