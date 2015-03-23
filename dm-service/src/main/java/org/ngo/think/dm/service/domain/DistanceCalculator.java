package org.ngo.think.dm.service.domain;

import org.ngo.think.dm.persistence.entity.Donor;
import org.ngo.think.dm.persistence.entity.DonorAddressDetail;
import org.ngo.think.dm.service.rest.RestServiceInvoker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DistanceCalculator
{
	@Autowired
	RestServiceInvoker restServiceInvoker;

	public void populateDistance(String donationCenterAddress, Donor donor)
	{
		donor.setDistanceInMeter(100);
		donor.setDistanceInKm("NA");
		
		if (null != donor.getDonorAddressDetails() && donor.getDonorAddressDetails().size() == 0)
		{
			System.out.println("No Address Configured for Donor : " + donor.getFirstName()+" "+donor.getLastName());
		}
		else if (null != donationCenterAddress)
		{
			DonorAddressDetail addressDetail = donor.getDonorAddressDetails().get(0);

			String donorLocation = addressDetail.getCity() + "," + addressDetail.getPinCode() + "," + addressDetail.getState();
			String centerLocation = donationCenterAddress;

			LocationResposne locationResposne = restServiceInvoker.invokeRestService(donorLocation, centerLocation);

			if (null != locationResposne.getRows() && !locationResposne.getRows().isEmpty())
			{
				RowsHolder rowsHolder = locationResposne.getRows().get(0);

				if (null != rowsHolder.getElements() && !rowsHolder.getElements().isEmpty())
				{
					DistanceDurationHolder distanceDurationHolder = rowsHolder.getElements().get(0);
					String distanceInKm = distanceDurationHolder.getDistance().getText();
					Integer distanceInMeter = Integer.valueOf(distanceDurationHolder.getDistance().getValue());
					donor.setDistanceInMeter(distanceInMeter);
					donor.setDistanceInKm(distanceInKm);
				}

			}

		}
	}
}


