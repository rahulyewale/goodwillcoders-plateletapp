package org.ngo.think.dm.persistence.dao;

import java.util.List;

import org.ngo.think.dm.persistence.entity.Donor;
import org.ngo.think.dm.persistence.generic.dao.BaseDAO;

public interface DonorDAO  extends BaseDAO<Donor>
{
	List<Donor> getAllDonors();
}
