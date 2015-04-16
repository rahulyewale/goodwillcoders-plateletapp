package org.ngo.think.dm.service.domain;

import java.util.Comparator;

import org.ngo.think.dm.persistence.entity.Donor;

public class DistanceComparator implements Comparator<Donor>
{

	@Override
	public int compare(Donor donor1, Donor donor2)
	{
			Double distance1 = Double.valueOf(donor1.getDistanceInMeter());

			Double distance2 = Double.valueOf(donor2.getDistanceInMeter());

			return Double.compare(distance1.doubleValue(), distance2.doubleValue());

	}
}
