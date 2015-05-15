package org.ngo.think.dm.persistence.dao;

import java.util.Date;
import java.util.List;

import org.ngo.think.dm.common.dto.GetRequestListInputDTO;
import org.ngo.think.dm.persistence.entity.UniqueRequestTxn;
import org.ngo.think.dm.persistence.generic.dao.BaseDAO;

public interface UniqueRequestDAO  extends BaseDAO<UniqueRequestTxn>
{

	UniqueRequestTxn getUniqueRequestTxnByDateCenterAndBloodGroup(Date requestDate, Long donationCenterId, String bloodGroup);
	
	UniqueRequestTxn getUniqueRequestTxnByRequestID(String requestId);
	
	List<UniqueRequestTxn> getRequestList(GetRequestListInputDTO getRequestListInputDTO);
	
}
