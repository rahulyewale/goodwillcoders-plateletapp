package org.ngo.think.dm.persistence.dao;

import org.ngo.think.dm.persistence.entity.PostalCodeMaster;
import org.ngo.think.dm.persistence.generic.dao.BaseDAO;

public interface PostalCodeMasterDAO  extends BaseDAO<PostalCodeMaster>
{
	PostalCodeMaster getPostalCodeMasterByPostCode(String postalCode);
}
