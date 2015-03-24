package org.ngo.think.dm.service.domain;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.ngo.think.dm.common.dto.DonorAppointmentDTO;
import org.ngo.think.dm.common.dto.SearchDonorRequestDTO;
import org.ngo.think.dm.common.util.DateUtil;
import org.ngo.think.dm.persistence.entity.DonationCenter;
import org.ngo.think.dm.persistence.entity.Donor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	
	@Value("${max.donation.limit.for.last.12.months}")
	private String maxDonationLimitForLast12Months;
	
	@Value("${next.donation.interval.in.days}")
	private String nextDonationInterval;

	public void filterDonorsBasedOnSearchCriteria(SearchDonorRequestDTO searchDonorRequestDTO, List<Donor> donorList, DonationCenter center, DonorAppointmentDTO appointmentDTO)
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
			else if(communicationHistoryManager.isDonorAlreadyConfirmed(donor,appointmentDTO))
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
					populateNextAvailableDate(donor,donationInfo);
				}

			}
			
			distanceCalculator.populateDistance(donor,center);
			populateRating(donor,donationInfo);
			donor.setLastDonationDate(donationInfo.getLastDonationDate());
			
		}	
			
	}

	private void populateRating(Donor donor, DonationInfo donationInfo)
	{
		int rating = 0;
		int totalNumberOfYear = donationInfo.getTotalNoOfDonationYears();
		int totalNoOfDonations = donationInfo.getTotalNoOfDonationsTillDate();
		
		if(totalNumberOfYear!=0)
		{
			rating  = (totalNoOfDonations/totalNumberOfYear)*(6/Integer.valueOf(maxDonationLimitForLast12Months));
		}
		
		donor.setRating(rating);
	}

	private void populateNextAvailableDate(Donor donor, DonationInfo donationInfo)
	{
		donor.setNextAvailableDate(DateUtil.addDaysToDate(donationInfo.getLastDonationDate(),Integer.valueOf(nextDonationInterval)));
	}

	private boolean isNextAvailableDateApplicable(DonationInfo donationInfo)
	{
		if (null!=donationInfo.getLastDonationDate()&&(DateUtil.getNumberOfDaysBetweenDates(donationInfo.getLastDonationDate(), new Date()) < Integer.valueOf(nextDonationInterval)))
		{
			return true;
		}
		return false;
	}

	private boolean isLastDonationDateGreaterThanNotDonatedInLastMonthsDate(SearchDonorRequestDTO donorRequestDTO, DonationInfo donationInfo)
	{
		Date referenceDate = DateUtil.deductMonthsFromDate(new Date(), donorRequestDTO.getNotDonatedInLastMonthsCount());
		
		if(null!=donationInfo.getLastDonationDate()&& donationInfo.getLastDonationDate().after(referenceDate))
		{
			return true;
		}
		
		return false;
	}

	private boolean isCoolingOfPeriodApplicable(SearchDonorRequestDTO donorRequestDTO, DonationInfo donationInfo)
	{
		if (null != donationInfo.getLastDonationDate() && (DateUtil.getNumberOfDaysBetweenDates(donationInfo.getLastDonationDate(), donorRequestDTO.getRequestDate()) < Integer.valueOf(nextDonationInterval)))
		{
			return true;
		}
		return false;
	}

	private boolean isNumberOfDonationsLessThanCountExeeded(int donationslessThanCount, DonationInfo donationInfo)
	{
		if (null != donationInfo.getTotalNoOfDonationInLast12Months())
		{
			return donationInfo.getTotalNoOfDonationInLast12Months() >= donationslessThanCount;
		}
		return false;
	}

	private boolean isMaxDonationLimitForLast12MonthsReached(DonationInfo donationInfo)
	{
		if (null != donationInfo.getTotalNoOfDonationInLast12Months())
		{
			return donationInfo.getTotalNoOfDonationInLast12Months() >= Integer.valueOf(maxDonationLimitForLast12Months);
		}
		return false;

	}

}
