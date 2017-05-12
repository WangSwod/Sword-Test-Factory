package com.sword.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static int getYear(Date date) {

		return initCalendar(date).get(Calendar.YEAR);

	}

	public static int getMonth(Date date) {

		return initCalendar(date).get(Calendar.MONTH);
	}

	public static int getDay(Date date) {

		return initCalendar(date).get(Calendar.DAY_OF_MONTH);
	}

	public static int getHour(Date date) {

		return initCalendar(date).get(Calendar.HOUR_OF_DAY);
	}

	public static int getMinute(Date date) {

		return initCalendar(date).get(Calendar.MINUTE);
	}

	public static int getSecond(Date date) {

		return initCalendar(date).get(Calendar.SECOND);
	}

	private static Calendar initCalendar(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}
}
