package org.ngo.think.dm.common.dto;

import java.io.Serializable;
import java.util.Date;

public class DonorAddressDetailsDTO implements Serializable
{

	private static final long serialVersionUID = 1L;

	private Long donorAddressId;
	private Long donorId;

	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	private String pinCode;
	private String createdBy;
	private Date createdDate;
	private String modifiedBy;
	private Date modifiedDate;

	public Long getDonorAddressId()
	{
		return donorAddressId;
	}

	public void setDonorAddressId(Long donorAddressId)
	{
		this.donorAddressId = donorAddressId;
	}

	public Long getDonorId()
	{
		return donorId;
	}

	public void setDonorId(Long donorId)
	{
		this.donorId = donorId;
	}

	public String getAddressLine1()
	{
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1)
	{
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2()
	{
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2)
	{
		this.addressLine2 = addressLine2;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public String getPinCode()
	{
		return pinCode;
	}

	public void setPinCode(String pinCode)
	{
		this.pinCode = pinCode;
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
