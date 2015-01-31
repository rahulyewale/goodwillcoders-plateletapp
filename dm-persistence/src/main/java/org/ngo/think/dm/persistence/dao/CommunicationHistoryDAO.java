package org.ngo.think.dm.persistence.dao;

import java.util.List;

import org.ngo.think.dm.common.dto.DonorAppointmentDTO;
import org.ngo.think.dm.persistence.entity.CommunicationHistory;
import org.ngo.think.dm.persistence.generic.dao.BaseDAO;

public interface CommunicationHistoryDAO  extends BaseDAO<CommunicationHistory>
{
	boolean isDonorConfirmedForGivenRequestDate(Long donorId);
	
	List<CommunicationHistory> getCommunicationHistoryForGivenCriteria(Long donorId, DonorAppointmentDTO donorAppointmentDTO);
}
