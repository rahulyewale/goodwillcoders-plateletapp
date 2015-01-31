package org.ngo.think.dm.common.dto;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DonationHistoryDTO implements Serializable
{

	private static final long serialVersionUID = 1L;

	private Integer donorHistoryId;
	private Integer donorId;
	private Date donationDate;
	private String donationComponentType;
	private boolean rejected;
	private String rejectedReason;
	private String remarks;
	private String createdBy;
	private Date createdDate;
	private String modifiedBy;
	private Date modifiedDate;

	public Integer getDonorHistoryId()
	{
		return donorHistoryId;
	}

	public void setDonorHistoryId(Integer donorHistoryId)
	{
		this.donorHistoryId = donorHistoryId;
	}

	public Integer getDonorId()
	{
		return donorId;
	}

	public void setDonorId(Integer donorId)
	{
		this.donorId = donorId;
	}

	public Date getDonationDate()
	{
		return donationDate;
	}

	public void setDonationDate(Date donationDate)
	{
		this.donationDate = donationDate;
	}

	public String getDonationComponentType()
	{
		return donationComponentType;
	}

	public void setDonationComponentType(String donationComponentType)
	{
		this.donationComponentType = donationComponentType;
	}

	public boolean isRejected()
	{
		return rejected;
	}

	public void setRejected(boolean rejected)
	{
		this.rejected = rejected;
	}

	public String getRejectedReason()
	{
		return rejectedReason;
	}

	public void setRejectedReason(String rejectedReason)
	{
		this.rejectedReason = rejectedReason;
	}

	public String getRemarks()
	{
		return remarks;
	}

	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
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

}
