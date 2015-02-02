import java.util.Comparator;

import org.ngo.think.dm.persistence.entity.Donor;


public class DistanceComparator implements Comparator<Donor>
{

	@Override
	public int compare(Donor donor1, Donor donor2)
	{
		return donor1.getDistanceInMeter()-donor2.getDistanceInMeter();
	}

}
