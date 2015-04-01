package org.ngo.think.dm.persistence.dao;

import java.util.List;

import org.ngo.think.dm.persistence.entity.DonationCenter;
import org.ngo.think.dm.persistence.generic.dao.BaseDAO;

public interface DonationCenterDAO  extends BaseDAO<DonationCenter>
{
	public List<DonationCenter> getDonationCenters();
}
