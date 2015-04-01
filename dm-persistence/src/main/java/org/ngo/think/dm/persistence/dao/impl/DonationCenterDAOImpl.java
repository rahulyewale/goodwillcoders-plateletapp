package org.ngo.think.dm.persistence.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.ngo.think.dm.persistence.dao.DonationCenterDAO;
import org.ngo.think.dm.persistence.entity.DonationCenter;
import org.ngo.think.dm.persistence.generic.dao.impl.BaseDAOImpl;
import org.springframework.stereotype.Repository;

@Repository
public class DonationCenterDAOImpl extends BaseDAOImpl<DonationCenter> implements DonationCenterDAO
{

	public List<DonationCenter> getDonationCenters()
	{
		Query namedQuery = getEntityManager().createNamedQuery("DonationCenter.findAll");

		@SuppressWarnings("unchecked")
		List<DonationCenter> donationCenterList = namedQuery.getResultList();

		return donationCenterList;

	}

}
