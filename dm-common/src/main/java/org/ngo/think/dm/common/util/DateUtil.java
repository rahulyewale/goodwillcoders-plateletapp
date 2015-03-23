package org.ngo.think.dm.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	
	public static Date addDaysToDate(Date referenceDate, int days)
	{
		return new DateTime(referenceDate).plusDays(days).toDate();
	}

	public static Date deductMonthsFromDate(Date referenceDate, int months)
	{
		return new DateTime(referenceDate).minusMonths(months).toDate();
	}
	
	public static String dateToString(Date referenceDate)
	{
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
	   
	   //to convert Date to String, use format method of SimpleDateFormat class.
	   String strDate = dateFormat.format(referenceDate);
	   return strDate;
	}
	
	public static Date stringToDate(String referenceStringDate) throws ParseException
	{
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	   
	   //to convert Date to String, use format method of SimpleDateFormat class.
	   Date date = dateFormat.parse(referenceStringDate);
	   return date;
	}
	
	
}
