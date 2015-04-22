package org.ngo.think.dm.persistence.dao.impl;

import org.ngo.think.dm.persistence.dao.DonationHistoryDAO;
import org.ngo.think.dm.persistence.entity.DonationHistory;
import org.ngo.think.dm.persistence.generic.dao.impl.BaseDAOImpl;
import org.springframework.stereotype.Repository;

@Repository
public class DonationHistoryDAOImpl extends BaseDAOImpl<DonationHistory> implements DonationHistoryDAO
{
}
