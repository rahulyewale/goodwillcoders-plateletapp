package org.ngo.think.dm.persistence.dao;

import java.util.Date;

import org.ngo.think.dm.persistence.entity.UniqueRequestTxn;
import org.ngo.think.dm.persistence.generic.dao.BaseDAO;

public interface UniqueRequestDAO  extends BaseDAO<UniqueRequestTxn>
{

	UniqueRequestTxn getUniqueRequestTxnByDateAndCenter(Date requestDate, Long donationCenterId);
	
}
