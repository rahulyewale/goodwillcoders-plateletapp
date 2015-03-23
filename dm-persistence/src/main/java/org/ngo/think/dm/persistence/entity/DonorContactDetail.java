package org.ngo.think.dm.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the donor_contact_details database table.
 */
@Entity
@Table(name = "donor_contact_details")
@NamedQuery(name = "DonorContactDetail.findAll", query = "SELECT d FROM DonorContactDetail d")
public class DonorContactDetail implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "DONOR_CONTACT_DETAILS_DONORCONTACTID_GENERATOR", sequenceName = "DONOR_CONTACT_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DONOR_CONTACT_DETAILS_DONORCONTACTID_GENERATOR")
	@Column(name = "donor_contact_id")
	private Long donorContactId;

	@Column(name = "contact_number")
	private String contactNumber;

	@Column(name = "contact_number_alternate")
	private String contactNumberAlternate;

	private String email;

	// bi-directional many-to-one association to Donor
	@ManyToOne
	@JoinColumn(name = "donor_id")
	private Donor donor;

	public DonorContactDetail()
	{
	}

	public Long getDonorContactId()
	{
		return this.donorContactId;
	}

	public void setDonorContactId(Long donorContactId)
	{
		this.donorContactId = donorContactId;
	}

	public String getContactNumber()
	{
		return this.contactNumber;
	}

	public void setContactNumber(String contactNumber)
	{
		this.contactNumber = contactNumber;
	}

	public String getContactNumberAlternate()
	{
		return this.contactNumberAlternate;
	}

	public void setContactNumberAlternate(String contactNumberAlternate)
	{
		this.contactNumberAlternate = contactNumberAlternate;
	}

	public String getEmail()
	{
		return this.email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public Donor getDonor()
	{
		return this.donor;
	}

	public void setDonor(Donor donor)
	{
		this.donor = donor;
	}

}