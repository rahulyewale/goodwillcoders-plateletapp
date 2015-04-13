package org.ngo.think.dm.service.domain;
import java.util.Comparator;

import org.ngo.think.dm.persistence.entity.UniqueRequestTxn;


public class DateComparator implements Comparator<UniqueRequestTxn>
{

	@Override
	public int compare(UniqueRequestTxn requestTxn1, UniqueRequestTxn requestTxn2)
	{
		return requestTxn2.getRequestDate().compareTo(requestTxn1.getRequestDate());
	}

}
