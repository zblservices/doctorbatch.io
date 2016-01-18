package com.ibm.bigbluebank;

import java.util.Calendar;
import java.util.Date;

public class DateUtility {
	public static long daysBetween(final Calendar startDate, final Calendar endDate)
	{
		long days = 0;
		while ( endDate.after(startDate) ) {
			endDate.add(Calendar.DATE, -1);
			++days;
		}
		
		return days;
	}
	
	public static long daysBetween(final Date startDate, final Date endDate) {
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		
		start.setTime( startDate );
		end.setTime( endDate );
		
		return daysBetween( start, end );
	}
	
	public static long yearsBetween( final Date startDate, final Date endDate ) {
		return daysBetween(startDate,endDate)/365l;
	}
}
