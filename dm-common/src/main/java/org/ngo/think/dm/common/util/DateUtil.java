package org.ngo.think.dm.common.util;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;

public class DateUtil
{

	public static int getNumberOfDaysBetweenDates(Date olderDate, Date newerDate)
	{
		DateTime startDateTime = new DateTime(olderDate.getTime());
		DateTime endDateTime = new DateTime(newerDate.getTime());
		Days days = Days.daysBetween(startDateTime, endDateTime);
		return days.getDays();
	}

	public static Date deductDaysFromDate(Date referenceDate, int days)
	{
		return new DateTime(referenceDate).minusDays(days).toDate();
	}

	public static Date deductMonthsFromDate(Date referenceDate, int months)
	{
		return new DateTime(referenceDate).minusMonths(months).toDate();
	}
}
