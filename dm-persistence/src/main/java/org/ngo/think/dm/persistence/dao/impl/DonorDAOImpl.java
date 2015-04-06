package org.ngo.think.dm.persistence.dao.impl;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Query;

import org.ngo.think.dm.persistence.dao.DonorDAO;
import org.ngo.think.dm.persistence.entity.Donor;
import org.ngo.think.dm.persistence.generic.dao.impl.BaseDAOImpl;
import org.springframework.stereotype.Repository;

@Repository
public class DonorDAOImpl extends BaseDAOImpl<Donor> implements DonorDAO
{

	@Override
	public List<Donor> getAllDonors(String bloodGroup)
	{
		Query query = getEntityManager().createNamedQuery("Donor.findAll", Donor.class);
		
		if (null != bloodGroup && bloodGroup.isEmpty())
		{
			List<String> allBloodGroups = Arrays.asList("A+","A-","B+","B-","O+","O-","AB+","AB-","OH+","OH-");
			query.setParameter("bloodGroup", allBloodGroups);
		}
		else
		{
			query.setParameter("bloodGroup", bloodGroup);
		}

		List<Donor> donors = query.getResultList();
		return donors;
	}

}
