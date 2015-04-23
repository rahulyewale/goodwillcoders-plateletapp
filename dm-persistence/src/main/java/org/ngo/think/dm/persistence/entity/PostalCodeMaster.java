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

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(lattitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((postalcode == null) ? 0 : postalcode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (getClass() != obj.getClass())
		{
			return false;
		}
		
		PostalCodeMaster otherPostalCode = (PostalCodeMaster) obj;
		if (Double.doubleToLongBits(lattitude) != Double.doubleToLongBits(otherPostalCode.lattitude))
		{
			return false;
		}
		
		if (Double.doubleToLongBits(longitude) != Double.doubleToLongBits(otherPostalCode.longitude))
		{
			return false;
		}
		if (postalcode == null)
		{
			if (otherPostalCode.postalcode != null)
				return false;
		}
		else if (!postalcode.equals(otherPostalCode.postalcode))
		{
			return false;
		}
		
		if(postalcode.equals(otherPostalCode.postalcode)
				&& (Double.doubleToLongBits(lattitude) != Double.doubleToLongBits(otherPostalCode.lattitude))
				&& (Double.doubleToLongBits(longitude) != Double.doubleToLongBits(otherPostalCode.longitude)))
		{
			return true;
		}
		return true;
	}
	
	//TODO remove
	public static void main(String[] args)
	{
		PostalCodeMaster codeMaster = new PostalCodeMaster();
		codeMaster.setPostalcode("123456");
		codeMaster.setLattitude(12.34);
		codeMaster.setLongitude(40.12);
		
		PostalCodeMaster codeMaster1 = new PostalCodeMaster();
		codeMaster1.setPostalcode("123456");
		codeMaster1.setLattitude(12.34);
		codeMaster1.setLongitude(40.12);
		
		PostalCodeMaster codeMaster2 = new PostalCodeMaster();
		codeMaster2.setPostalcode("123456");
		codeMaster2.setLattitude(12.35);
		codeMaster2.setLongitude(40.12);
		
		System.out.println(codeMaster.equals(codeMaster1));
		
		System.out.println(codeMaster.equals(codeMaster2));
		
		System.out.println(codeMaster1.equals(codeMaster2));
	}
}