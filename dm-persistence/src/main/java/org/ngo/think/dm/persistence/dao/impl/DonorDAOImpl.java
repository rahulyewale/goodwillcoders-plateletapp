package org.ngo.think.dm.persistence.dao.impl;

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
	public List<Donor> getAllDonors()
	{
		Query query = getEntityManager().createNamedQuery("Donor.findAll", Donor.class);

		List<Donor> donors = query.getResultList();
		return donors;
	}

}
