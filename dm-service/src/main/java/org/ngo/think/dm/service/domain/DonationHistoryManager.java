package org.ngo.think.dm.service.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.ngo.think.dm.common.util.DateUtil;
import org.ngo.think.dm.persistence.entity.DonationHistory;
import org.springframework.stereotype.Component;

@Component
public class DonationHistoryManager
{

	public DonationInfo deriveLastDonationDateAndDonationCountForLast12Months(List<DonationHistory> donationHistorylist)
	{

		
		Integer totalNoOfDonationInLast12Months = 0;
		Integer totalNoOfDonationsTillDate = 0;
		Set<String> years = new HashSet<String>();

		Date twelveMonthBeforeDate = DateUtil.deductMonthsFromDate(new Date(), 12);
		
		Date tempLastDonatedDate = null;
		for (Iterator<DonationHistory> iterator = donationHistorylist.iterator(); iterator.hasNext();)
		{
			DonationHistory donationHistory = (DonationHistory) iterator.next();
			
			//latest donation date
			if(tempLastDonatedDate == null)
			{
				tempLastDonatedDate = donationHistory.getDonationDate();
			}
			else if(tempLastDonatedDate.before(donationHistory.getDonationDate()))
			{
				tempLastDonatedDate = donationHistory.getDonationDate();
			}
			
			//unique years
			years.add(getYear(donationHistory.getDonationDate()));
			
			//successful donation
			if(! donationHistory.getIsrejected())
			{
				totalNoOfDonationsTillDate ++;
			}
			
			//last 12 months count
			if(donationHistory.getDonationDate().after(twelveMonthBeforeDate))
			{
				totalNoOfDonationInLast12Months ++;
			}
			
		}
		
		DonationInfo donationInfo = new DonationInfo();
		donationInfo.setTotalNoOfDonationYears(years.size());
		donationInfo.setLastDonationDate(tempLastDonatedDate);
		donationInfo.setTotalNoOfDonationsTillDate(totalNoOfDonationsTillDate);
		donationInfo.setTotalNoOfDonationInLast12Months(totalNoOfDonationInLast12Months);
		return donationInfo;
	}
	
	public static String getYear(Date inputDate)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
		return dateFormat.format(inputDate);
	}
}
