package org.ngo.think.dm.persistence.dao;

import org.ngo.think.dm.persistence.entity.CommunicationHistory;
import org.ngo.think.dm.persistence.generic.dao.BaseDAO;

public interface CommunicationHistoryDAO  extends BaseDAO<CommunicationHistory>
{
	boolean isDonorConfirmedForGivenRequestDate(Long donorId);
}
