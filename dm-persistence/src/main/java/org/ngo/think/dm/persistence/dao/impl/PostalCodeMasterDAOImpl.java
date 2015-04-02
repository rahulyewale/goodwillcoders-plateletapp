package org.ngo.think.dm.persistence.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.ngo.think.dm.persistence.dao.PostalCodeMasterDAO;
import org.ngo.think.dm.persistence.entity.PostalCodeMaster;
import org.ngo.think.dm.persistence.generic.dao.impl.BaseDAOImpl;
import org.springframework.stereotype.Repository;

@Repository
public class PostalCodeMasterDAOImpl extends BaseDAOImpl<PostalCodeMaster> implements PostalCodeMasterDAO
{

	@Override
	public PostalCodeMaster getPostalCodeMasterByPostCode(String postalCode)
	{
		PostalCodeMaster postalCodeMaster = null;
		Query query = getEntityManager().createNamedQuery("PostalCodeMaster.findByPostCode", PostalCodeMaster.class);

		query.setParameter("postalCode", postalCode);
		
		List<PostalCodeMaster> postalCodeMasters = query.getResultList();
		if (postalCodeMasters.size() > 0)
		{
			postalCodeMaster = postalCodeMasters.get(0);
		}
		return postalCodeMaster;
	}

}
