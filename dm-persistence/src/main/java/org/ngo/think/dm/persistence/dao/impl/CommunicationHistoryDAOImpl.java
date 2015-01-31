package org.ngo.think.dm.persistence.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.ngo.think.dm.persistence.dao.CommunicationHistoryDAO;
import org.ngo.think.dm.persistence.entity.CommunicationHistory;
import org.ngo.think.dm.persistence.generic.dao.impl.BaseDAOImpl;
import org.springframework.stereotype.Repository;

@Repository
public class CommunicationHistoryDAOImpl extends BaseDAOImpl<CommunicationHistory> implements CommunicationHistoryDAO
{

	@Override
	public boolean isDonorConfirmedForGivenRequestDate(Long donorId)
	{
		Query query = getEntityManager().createNamedQuery("CommunicationHistory.getCommunicationHistoryOfConfirmedDonor", CommunicationHistory.class);
		query.setParameter("status", "CONFIRMED");
		query.setParameter("requestedDate", new Date(), TemporalType.DATE);
		query.setParameter("donorId", donorId);

		List<CommunicationHistory> communicationHistories = query.getResultList();
		if (communicationHistories.isEmpty())
		{
			return false;
		}
		else
		{
			return true;
		}
	}

}
