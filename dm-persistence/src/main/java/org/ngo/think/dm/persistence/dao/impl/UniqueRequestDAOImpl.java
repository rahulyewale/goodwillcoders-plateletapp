package org.ngo.think.dm.persistence.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.ngo.think.dm.persistence.dao.UniqueRequestDAO;
import org.ngo.think.dm.persistence.entity.UniqueRequestTxn;
import org.ngo.think.dm.persistence.generic.dao.impl.BaseDAOImpl;
import org.springframework.stereotype.Repository;

@Repository
public class UniqueRequestDAOImpl extends BaseDAOImpl<UniqueRequestTxn> implements UniqueRequestDAO
{

	@Override
	public UniqueRequestTxn getUniqueRequestTxnByDateAndCenter(Date requestDate, Long donationCenterId)
	{

		Query query = getEntityManager().createNamedQuery("UniqueRequestTxn.getUniqueRequestTxnByDateAndCentre", UniqueRequestTxn.class);
		query.setParameter("requestDate", requestDate, TemporalType.DATE);
		query.setParameter("donationCentreId", donationCenterId);

		UniqueRequestTxn resultUniqueRequestTxn = null;
		List<UniqueRequestTxn> uniqueRequestTxns = query.getResultList();
		if (!uniqueRequestTxns.isEmpty())
		{
			resultUniqueRequestTxn = uniqueRequestTxns.get(0);
		}

		return resultUniqueRequestTxn;
	}
}
