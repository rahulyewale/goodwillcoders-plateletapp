package org.ngo.think.dm.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the postal_code_master database table.
 */
@Entity
@Table(name = "postal_code_master")
@NamedQueries({
								@NamedQuery(name = "PostalCodeMaster.findAll", query = "SELECT p FROM PostalCodeMaster p"),
								@NamedQuery(name = "PostalCodeMaster.findByPostCode", query = "SELECT p FROM PostalCodeMaster p where postalcode=:postalCode")})
public class PostalCodeMaster implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "POSTAL_CODE_MASTER_POSTALCODEMSTID_GENERATOR", sequenceName = "POSTAL_CODE_MASTER_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POSTAL_CODE_MASTER_POSTALCODEMSTID_GENERATOR")
	@Column(name = "postal_code_mst_id")
	private Long postalCodeMstId;

	private double lattitude;

	private double longitude;

	private String postalcode;

	public PostalCodeMaster()
	{
	}

	public Long getPostalCodeMstId()
	{
		return this.postalCodeMstId;
	}

	public void setPostalCodeMstId(Long postalCodeMstId)
	{
		this.postalCodeMstId = postalCodeMstId;
	}

	public double getLattitude()
	{
		return this.lattitude;
	}

	public void setLattitude(double lattitude)
	{
		this.lattitude = lattitude;
	}

	public double getLongitude()
	{
		return this.longitude;
	}

	public void setLongitude(double longitude)
	{
		this.longitude = longitude;
	}

	public String getPostalcode()
	{
		return this.postalcode;
	}

	public void setPostalcode(String postalcode)
	{
		this.postalcode = postalcode;
	}

}