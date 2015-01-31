package org.ngo.think.dm.common.dto;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DonorContactDetailsDTO implements Serializable
{

	private static final long serialVersionUID = 1L;

	private Long donorContactId;
	private Long donorId;

	private String contactNumber;
	private String contactNumberAlternate;
	private String email;

	public Long getDonorContactId()
	{
		return donorContactId;
	}

	public void setDonorContactId(Long donorContactId)
	{
		this.donorContactId = donorContactId;
	}

	public Long getDonorId()
	{
		return donorId;
	}

	public void setDonorId(Long donorId)
	{
		this.donorId = donorId;
	}

	public String getContactNumber()
	{
		return contactNumber;
	}

	public void setContactNumber(String contactNumber)
	{
		this.contactNumber = contactNumber;
	}

	public String getContactNumberAlternate()
	{
		return contactNumberAlternate;
	}

	public void setContactNumberAlternate(String contactNumberAlternate)
	{
		this.contactNumberAlternate = contactNumberAlternate;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

}
