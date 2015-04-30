package org.ngo.think.dm.service.domain;

import java.text.DecimalFormat;
import java.util.Set;

import org.ngo.think.dm.persistence.dao.PostalCodeMasterDAO;
import org.ngo.think.dm.persistence.entity.DonationCenter;
import org.ngo.think.dm.persistence.entity.Donor;
import org.ngo.think.dm.persistence.entity.DonorAddressDetail;
import org.ngo.think.dm.persistence.entity.PostalCodeMaster;
import org.ngo.think.dm.service.rest.RestServiceInvoker;
import org.ngo.think.dm.service.util.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DistanceCalculator
{
	@Autowired
	private RestServiceInvoker restServiceInvoker;

	@Autowired
	private PostalCodeMasterDAO postalCodeMasterDAO;

	public void populateDistance(Donor donor, DonationCenter center, boolean useDistanceMatrixApi, Set<PostalCodeMaster> uniquePostalCodeMasters)
	{
		donor.setDistanceInMeter(8000);
		donor.setDistanceInKm("NA");

		if (useDistanceMatrixApi)
		{

			if (null != donor.getDonorAddressDetails() && donor.getDonorAddressDetails().size() == 0)
			{
				System.out.println("No Address Configured for Donor : " + donor.getFirstName() + " " + donor.getLastName());
			}
			else if (null != center.getAddressLine1())
			{
				DonorAddressDetail addressDetail = donor.getDonorAddressDetails().get(0);

				String donorLocation = addressDetail.getCity() + "," + addressDetail.getPinCode() + "," + addressDetail.getState();
				String centerLocation = center.getCity() + "," + center.getPinCode() + "," + center.getState();

				LocationResponse locationResposne = restServiceInvoker.invokeRestService(donorLocation, centerLocation);

				if (null != locationResposne && null != locationResposne.getRows() && !locationResposne.getRows().isEmpty())
				{
					RowsHolder rowsHolder = locationResposne.getRows().get(0);

					if (null != rowsHolder.getElements() && !rowsHolder.getElements().isEmpty())
					{
						DistanceDurationHolder distanceDurationHolder = rowsHolder.getElements().get(0);
						String distanceInKm = distanceDurationHolder.getDistance().getText();
						Double distanceInMeter = Double.valueOf(distanceDurationHolder.getDistance().getValue());
						donor.setDistanceInMeter(distanceInMeter);
						donor.setDistanceInKm(distanceInKm);
					}

				}

			}

		}
		else
		{
			// Use Longitude/Lattitude API
			PostalCodeMaster donorPostalCodeMaster = postalCodeMasterDAO.getPostalCodeMasterByPostCode(donor.getDonorAddressDetails().get(0).getPinCode());
			//PostalCodeMaster centerPostalCodeMaster = postalCodeMasterDAO.getPostalCodeMasterByPostCode(center.getPinCode());

			if (null != donorPostalCodeMaster)//&& null != centerPostalCodeMaster)
			{
				//add in hashset
				ServiceUtil.handleUniquePostalCodes(uniquePostalCodeMasters, donorPostalCodeMaster);
				
				donor.getDonorAddressDetails().get(0).setLongitude(donorPostalCodeMaster.getLongitude());
				donor.getDonorAddressDetails().get(0).setLattitude(donorPostalCodeMaster.getLattitude());
				
				double distanceInKM = calculateDistanceByLongitudeLattitude(donorPostalCodeMaster.getLattitude(), donorPostalCodeMaster.getLongitude(), center.getLattitude(), center.getLongitude());
				donor.setDistanceInKm(String.valueOf(roundTo2Decimals(distanceInKM)));
				donor.setDistanceInMeter(distanceInKM);
				System.out.println("Donor : " + donor.getFirstName() + " " + donor.getLastName() + " Donor Pin Code : " + donor.getDonorAddressDetails().get(0).getPinCode() + " Center Pin Code : " + center.getPinCode() + " Distance: " + distanceInKM);
			}
			else
			{
				System.out.println("No Postal Code Master Found for either Donor or Center: Donor Pin Code : " + donor.getDonorAddressDetails().get(0).getPinCode() + " Center Pin Code : " + center.getPinCode());
			}

		}
	}

	/*private void handleUniquePostalCodes(Set<PostalCodeMaster> uniquePostalCodeMasters, PostalCodeMaster donorPostalCodeMaster)
	{
		boolean successfullyAdded = uniquePostalCodeMasters.add(donorPostalCodeMaster);
		if(!successfullyAdded)
		{
			//if false => duplicate
			//then add offset, update postalcodemaster 
			addOffsetToCoordinates(donorPostalCodeMaster);
		   
			//add in hashset
		   uniquePostalCodeMasters.add(donorPostalCodeMaster);
		}
	}

	private void addOffsetToCoordinates(PostalCodeMaster donorPostalCodeMaster)
	{
		double newLattitude = donorPostalCodeMaster.getLattitude() + (Math.random() -.5) / 1500;
		double newLongitude = donorPostalCodeMaster.getLongitude() + (Math.random() -.5) / 1500;
		donorPostalCodeMaster.setLattitude(newLattitude);
		donorPostalCodeMaster.setLongitude(newLongitude);
	}*/

	private double calculateDistanceByLongitudeLattitude(double lat1, double lon1, double lat2, double lon2)
	{
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;

		dist = dist * 1.609344;

		return (dist);
	}

	private double deg2rad(double deg)
	{
		return (deg * Math.PI / 180.0);
	}

	private double rad2deg(double rad)
	{
		return (rad * 180 / Math.PI);
	}

	private static double roundTo2Decimals(double val)
	{
		DecimalFormat df2 = new DecimalFormat("###.##");
		return Double.valueOf(df2.format(val));
	}

}
