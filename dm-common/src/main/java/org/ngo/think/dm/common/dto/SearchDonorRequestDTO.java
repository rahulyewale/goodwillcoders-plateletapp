package org.ngo.think.dm.common.dto;

import java.io.Serializable;
import java.util.Date;

public class SearchDonorRequestDTO implements Serializable
{

	private static final long serialVersionUID = 1L;
	private String donationType;

	private String bloodGroup;

	private Date requestDate;

	private Long donationCentre;

	private Integer numberOfDonationsLessThanCount;

	private Integer notDonatedInLastMonthsCount;

	private boolean searchAvailableDonorsOnly;

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

}
