package org.ngo.think.dm.persistence.dao.impl;

import org.ngo.think.dm.persistence.dao.UniqueRequestDAO;
import org.ngo.think.dm.persistence.entity.UniqueRequestTxn;
import org.ngo.think.dm.persistence.generic.dao.impl.BaseDAOImpl;
import org.springframework.stereotype.Repository;

@Repository
public class UniqueRequestDAOImpl extends BaseDAOImpl<UniqueRequestTxn> implements UniqueRequestDAO
{

}
