package org.ngo.think.dm.service.domain;

import java.util.Date;

public class DonationInfo
{
	private Integer totalNoOfDonationInLast12Months;
	private Date lastDonationDate;
	private Integer totalNoOfDonationsTillDate;
	private Integer totalNoOfDonationYears;

	public Integer getTotalNoOfDonationInLast12Months()
	{
		return totalNoOfDonationInLast12Months;
	}

	public void setTotalNoOfDonationInLast12Months(Integer totalNoOfDonationInLast12Months)
	{
		this.totalNoOfDonationInLast12Months = totalNoOfDonationInLast12Months;
	}

	public Date getLastDonationDate()
	{
		return lastDonationDate;
	}

	public void setLastDonationDate(Date lastDonationDate)
	{
		this.lastDonationDate = lastDonationDate;
	}

	public Integer getTotalNoOfDonationsTillDate()
	{
		return totalNoOfDonationsTillDate;
	}

	public void setTotalNoOfDonationsTillDate(Integer totalNoOfDonationsTillDate)
	{
		this.totalNoOfDonationsTillDate = totalNoOfDonationsTillDate;
	}

	public Integer getTotalNoOfDonationYears()
	{
		return totalNoOfDonationYears;
	}

	public void setTotalNoOfDonationYears(Integer totalNoOfDonationYears)
	{
		this.totalNoOfDonationYears = totalNoOfDonationYears;
	}

}
