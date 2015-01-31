package org.ngo.think.dm.service.domain;

import java.awt.PageAttributes.OriginType;

import org.ngo.think.dm.persistence.entity.DonationCenter;
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

	public void populateDistance(Donor donor, DonationCenter center)
	{
		DonorAddressDetail addressDetail = donor.getDonorAddressDetails().get(0);
		
		String donorLocation = addressDetail.getCity()+","+addressDetail.getPinCode()+","+addressDetail.getState();
		String centerLocation = center.getCity()+","+center.getPinCode()+","+center.getState();
		
		LocationResposne locationResposne = restServiceInvoker.invokeRestService(donorLocation, centerLocation);
		
		if(null!=locationResposne.getRows() && !locationResposne.getRows().isEmpty())
		{
			RowsHolder rowsHolder = locationResposne.getRows().get(0);
			
			if(null!=rowsHolder.getElements() && !rowsHolder.getElements().isEmpty())
			{
				DistanceDurationHolder distanceDurationHolder = rowsHolder.getElements().get(0);
				double distanceInKm = Double.valueOf(distanceDurationHolder.getDistance().getValue());
				donor.setDistanceInKm(distanceInKm);
			}
			
		}
		
	}
}


