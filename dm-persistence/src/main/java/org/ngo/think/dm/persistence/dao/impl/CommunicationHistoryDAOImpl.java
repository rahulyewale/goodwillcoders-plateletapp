package org.ngo.think.dm.persistence.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.ngo.think.dm.common.constant.CommonConstants;
import org.ngo.think.dm.common.dto.DonorAppointmentDTO;
import org.ngo.think.dm.common.dto.SearchCommunicationHistoryRequestDTO;
import org.ngo.think.dm.persistence.dao.CommunicationHistoryDAO;
import org.ngo.think.dm.persistence.entity.CommunicationHistory;
import org.ngo.think.dm.persistence.generic.dao.impl.BaseDAOImpl;
import org.springframework.stereotype.Repository;

@Repository
public class CommunicationHistoryDAOImpl extends BaseDAOImpl<CommunicationHistory> implements CommunicationHistoryDAO
{

	@Override
	public boolean isDonorConfirmedOnOrAfterRequirementDate(Long donorId, Date requirementDate)
	{
		Query query = getEntityManager().createNamedQuery("CommunicationHistory.getCommunicationHistoryOfConfirmedDonor", CommunicationHistory.class);
		query.setParameter("requestedDate", requirementDate, TemporalType.DATE);
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

	@Override
	public List<CommunicationHistory> getCommunicationHistoryOfDonorOnOrAfterRequirementDate(Long donorId, Date requirementDate)
	{
		Query query = getEntityManager().createNamedQuery("CommunicationHistory.getCommunicationHistoryOfDonorOnOrAfterRequirementDate", CommunicationHistory.class);
		query.setParameter("today", new Date(), TemporalType.DATE);
		query.setParameter("donorId", donorId);

		return query.getResultList();
	}

	@Override
	public List<CommunicationHistory> getCommunicationHistoryForGivenCriteria(Long donorId, DonorAppointmentDTO donorAppointmentDTO)
	{
		
		Query query = getEntityManager().createNamedQuery("CommunicationHistory.getCommunicationHistoryForSMSCheck", CommunicationHistory.class);
		query.setParameter("requestId", donorAppointmentDTO.getRequestTxnId());
		query.setParameter("donorId", donorId);
		query.setParameter("requestedDate", donorAppointmentDTO.getRequestedDate(), TemporalType.DATE);
		query.setParameter("donationCenterId", donorAppointmentDTO.getCenterId());

		List<CommunicationHistory> communicationHistories = query.getResultList();
		return communicationHistories;
	}
	
	@Override
	public List<CommunicationHistory> getDonatedStateCommunicationHistoryByRequest(String requestId)
	{
		Query query = getEntityManager().createNamedQuery("CommunicationHistory.getDonatedStateCommunicationHistoryOfRequest", CommunicationHistory.class);
		query.setParameter("requestId", requestId);
		query.setParameter("status", CommonConstants.HistoryStatus.DONATED);

		List<CommunicationHistory> communicationHistories = query.getResultList();
		return communicationHistories;
	}

	@Override
	public List<CommunicationHistory> getCommunicationHistoryForScreen(SearchCommunicationHistoryRequestDTO searchCommunicationHistoryRequestDTO)
	{

		String baseQuery = "SELECT u FROM CommunicationHistory u";
		String whereClause = " WHERE";

		if (null != searchCommunicationHistoryRequestDTO.getRequestTxnId() && !searchCommunicationHistoryRequestDTO.getRequestTxnId().isEmpty())
		{
			baseQuery = baseQuery + whereClause + " u.requestId =:requestId";
			whereClause = " AND";
		}

		if (null != searchCommunicationHistoryRequestDTO.getDonationRequestDate())
		{
			baseQuery = baseQuery + whereClause + " u.requestedDate =:requestedDate";
			whereClause = " AND";
		}

		if (null != searchCommunicationHistoryRequestDTO.getMobileNumber() && !searchCommunicationHistoryRequestDTO.getMobileNumber().isEmpty())
		{
			baseQuery = baseQuery + whereClause + " u.mobileNumber =:mobileNumber";
			whereClause = " AND";
		}

		if (null != searchCommunicationHistoryRequestDTO.getStatus() && !searchCommunicationHistoryRequestDTO.getStatus().isEmpty() && !searchCommunicationHistoryRequestDTO.getStatus().equals("-1"))
		{
			baseQuery = baseQuery + whereClause + " u.status =:status";
		}

		Query query = getEntityManager().createQuery(baseQuery);
		
		
		if (null != searchCommunicationHistoryRequestDTO.getRequestTxnId() && !searchCommunicationHistoryRequestDTO.getRequestTxnId().isEmpty())
		{
			query.setParameter("requestId", searchCommunicationHistoryRequestDTO.getRequestTxnId());
		}

		if (null != searchCommunicationHistoryRequestDTO.getDonationRequestDate())
		{
			query.setParameter("requestedDate", searchCommunicationHistoryRequestDTO.getDonationRequestDate(), TemporalType.DATE);
		}

		if (null != searchCommunicationHistoryRequestDTO.getMobileNumber() && !searchCommunicationHistoryRequestDTO.getMobileNumber().isEmpty())
		{
			query.setParameter("mobileNumber", searchCommunicationHistoryRequestDTO.getMobileNumber());
		}

		if (null != searchCommunicationHistoryRequestDTO.getStatus() && !searchCommunicationHistoryRequestDTO.getStatus().isEmpty() && !searchCommunicationHistoryRequestDTO.getStatus().equals("-1"))
		{
			query.setParameter("status", searchCommunicationHistoryRequestDTO.getStatus());
		}
		

		List<CommunicationHistory> communicationHistories = query.getResultList();
		return communicationHistories;

	}
}
