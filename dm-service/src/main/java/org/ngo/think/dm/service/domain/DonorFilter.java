package org.ngo.think.dm.service.domain;

import java.util.Iterator;
import java.util.List;

import org.ngo.think.dm.common.dto.SearchDonorRequestDTO;
import org.ngo.think.dm.persistence.entity.Donor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DonorFilter
{
	
	@Autowired
	private DonationHistoryManager donationHistoryManager;
	
	@Autowired
	private CommunicationHistoryManager communicationHistoryManager;
	
	@Autowired
	private DistanceCalculator distanceCalculator;

	public void filterDonorsBasedOnSearchCriteria(SearchDonorRequestDTO searchDonorRequestDTO, List<Donor> donorList)
	{
		Iterator<Donor> donorIterator = donorList.iterator();
		
		while(donorIterator.hasNext())
		{
			Donor donor = donorIterator.next();
			
			DonationInfo donationInfo = donationHistoryManager.deriveLastDonationDateAndDonationCountForLast12Months(donor.getDonationHistories());
			
			if(isMaxDonationLimitForLast12MonthsReached(donationInfo))
			{
				donorIterator.remove();
				continue;
			}
			else if (isNumberOfDonationsLessThanCountExeeded(searchDonorRequestDTO.getNumberOfDonationsLessThanCount(), donationInfo))
			{
				donorIterator.remove();
				continue;
			}
			else if(isCoolingOfPeriodApplicable(searchDonorRequestDTO, donationInfo))
			{
				donorIterator.remove();
				continue;
			}	
			else if(isLastDonationDateGreaterThanNotDonatedInLastMonthsDate(searchDonorRequestDTO, donationInfo))
			{
				donorIterator.remove();
				continue;
			}	
			else if(communicationHistoryManager.isDonorAlreadyConfirmed(donor))
			{
				donorIterator.remove();
				continue;
			}
			else if(isNextAvailableDateApplicable(donationInfo))
			{
				if (searchDonorRequestDTO.getSearchAvailableDonorsOnly())
				{
					donorIterator.remove();
					continue;
				}
				else
				{
					populateNextAvailableDate(donor);
				}

			}
			
			distanceCalculator.populateDistance(donor,searchDonorRequestDTO);
			populateRating(donor,donationInfo);
			
		}	
			
	}

	private void populateRating(Donor donor, DonationInfo donationInfo)
	{
		// TODO Auto-generated method stub
		
	}

	private void populateNextAvailableDate(Donor donor)
	{
		// TODO Auto-generated method stub
		
	}

	private boolean isNextAvailableDateApplicable(DonationInfo donationInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	private boolean isLastDonationDateGreaterThanNotDonatedInLastMonthsDate(SearchDonorRequestDTO donorRequestDTO, DonationInfo donationInfo)
	{
		return false;
	}

	private boolean isCoolingOfPeriodApplicable(SearchDonorRequestDTO donorRequestDTO, DonationInfo donationInfo)
	{
		return true;
	}

	private boolean isNumberOfDonationsLessThanCountExeeded(int donationslessThanCount, DonationInfo donationInfo)
	{
		return donationInfo.getTotalNoOfDonationInLast12Months() >= donationslessThanCount;
	}

	private boolean isMaxDonationLimitForLast12MonthsReached(DonationInfo donationInfo)
	{
		return donationInfo.getTotalNoOfDonationInLast12Months()>=24;
	}

}
