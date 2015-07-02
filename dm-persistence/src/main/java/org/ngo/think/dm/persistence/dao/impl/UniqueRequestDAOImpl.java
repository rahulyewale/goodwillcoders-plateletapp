package org.ngo.think.dm.persistence.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.ngo.think.dm.common.dto.GetRequestListInputDTO;
import org.ngo.think.dm.persistence.dao.UniqueRequestDAO;
import org.ngo.think.dm.persistence.entity.UniqueRequestTxn;
import org.ngo.think.dm.persistence.generic.dao.impl.BaseDAOImpl;
import org.springframework.stereotype.Repository;

@Repository
public class UniqueRequestDAOImpl extends BaseDAOImpl<UniqueRequestTxn> implements UniqueRequestDAO
{

	@Override
	public UniqueRequestTxn getUniqueRequestTxnByDatePatientNameAndBloodGroup(Date requestDate, String patientName, String bloodGroup)
	{

		Query query = getEntityManager().createNamedQuery("UniqueRequestTxn.getUniqueRequestTxnByDatePatientNameAndBloodGroup", UniqueRequestTxn.class);
		query.setParameter("requestDate", requestDate, TemporalType.DATE);
		//query.setParameter("donationCentreId", donationCenterId);
		query.setParameter("patientName", patientName);
		query.setParameter("bloodGroup", bloodGroup);

		UniqueRequestTxn resultUniqueRequestTxn = null;
		List<UniqueRequestTxn> uniqueRequestTxns = query.getResultList();
		if (!uniqueRequestTxns.isEmpty())
		{
			resultUniqueRequestTxn = uniqueRequestTxns.get(0);
		}

		return resultUniqueRequestTxn;
	}
	
	@Override
	public UniqueRequestTxn getUniqueRequestTxnByRequestID(String requestId)
	{

		Query query = getEntityManager().createNamedQuery("UniqueRequestTxn.getUniqueRequestTxnByID", UniqueRequestTxn.class);
		query.setParameter("requestId", requestId);

		UniqueRequestTxn resultUniqueRequestTxn = null;
		List<UniqueRequestTxn> uniqueRequestTxns = query.getResultList();
		if (!uniqueRequestTxns.isEmpty())
		{
			resultUniqueRequestTxn = uniqueRequestTxns.get(0);
		}

		return resultUniqueRequestTxn;
	}
	
	@Override
	public List<UniqueRequestTxn> getRequestList(GetRequestListInputDTO getRequestListInputDTO)
	{
		String baseQuery = "SELECT u FROM UniqueRequestTxn u";
		String whereClause = " WHERE";

		if (null != getRequestListInputDTO.getRequestNumber() && !getRequestListInputDTO.getRequestNumber().isEmpty())
		{
			baseQuery = baseQuery + whereClause + " u.requestId =:requestId";
			whereClause = " AND";
		}

		if (null != getRequestListInputDTO.getDonationRequestFromDate() && null != getRequestListInputDTO.getDonationRequestToDate())
		{
			baseQuery = baseQuery + whereClause + " u.requestDate >=:fromDate AND u.requestDate <=:toDate";
			whereClause = " AND";

		}
		
		/* if (null != getRequestListInputDTO.getDonationCenterId() && getRequestListInputDTO.getDonationCenterId() != -1)
		{
			baseQuery = baseQuery + whereClause + " u.donationCenter.donationCenterId =:donationCenterId";
			whereClause = " AND";

		}
		*/
		
		if (null != getRequestListInputDTO.getStatus() && !getRequestListInputDTO.getStatus().isEmpty())
		{
			baseQuery = baseQuery + whereClause + " u.requestStatus =:requestStatus";
		}

		if (null != getRequestListInputDTO.getStatus() && !getRequestListInputDTO.getStatus().isEmpty())
		{
			baseQuery = baseQuery + whereClause + " u.requestStatus =:requestStatus";
		}

		Query query = getEntityManager().createQuery(baseQuery);

		if (null != getRequestListInputDTO.getRequestNumber() && !getRequestListInputDTO.getRequestNumber().isEmpty())
		{
			query.setParameter("requestId", getRequestListInputDTO.getRequestNumber());
		}

		if (null != getRequestListInputDTO.getDonationRequestFromDate() && null != getRequestListInputDTO.getDonationRequestToDate())
		{
			query.setParameter("fromDate", getRequestListInputDTO.getDonationRequestFromDate(), TemporalType.DATE);
			query.setParameter("toDate", getRequestListInputDTO.getDonationRequestToDate(), TemporalType.DATE);
		}
		if (null != getRequestListInputDTO.getDonationCenterId() && getRequestListInputDTO.getDonationCenterId() != -1)
		{
			query.setParameter("donationCenterId", getRequestListInputDTO.getDonationCenterId());

		}

		if (null != getRequestListInputDTO.getStatus() && !getRequestListInputDTO.getStatus().isEmpty())
		{
			query.setParameter("requestStatus", getRequestListInputDTO.getStatus());
		}
		
		if (null != getRequestListInputDTO.getPatientName() && !getRequestListInputDTO.getPatientName().isEmpty())
		{
			query.setParameter("patientName", getRequestListInputDTO.getPatientName());
		}

		List<UniqueRequestTxn> requestTxnList = query.getResultList();
		return requestTxnList;
	}
	
	
	
}
