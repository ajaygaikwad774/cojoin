package com.smn.el.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static Date addNoOfDaysInCurrentDate(int noOfDays) {
		System.out.println("Entering addNoOfDaysInCurrentDate");
		Date dt = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 
		c.add(Calendar.DATE, noOfDays);
		dt = c.getTime();
		System.out.println("Existing addNoOfDaysInCurrentDate");
		return dt;
	}
	
	public static Date addNoOfMonthsInCurrentDate(int noOfDays) {
		System.out.println("Entering addNoOfDaysInCurrentDate");
		Date dt = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 
		c.add(Calendar.MONTH, noOfDays);
		dt = c.getTime();
		System.out.println("Existing addNoOfDaysInCurrentDate");
		return dt;
	}
	
	public static Date addHoursInCurrentDate(int hours) {
		Calendar cal = Calendar.getInstance(); // creates calendar
		cal.setTime(new Date());               // sets calendar time/date
		cal.add(Calendar.HOUR_OF_DAY, hours);      // adds one hour
		return cal.getTime();  
	}
	
	public static String formatCurrentDate(String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = new Date();
       return dateFormat.format(date);
	}
	
	
	/**
	 * @method is used for get current date in specific format
	 * @param format
	 * @return
	 */
	public static String getCurrentDateInString(String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = new Date();
        return dateFormat.format(date).toString();
	}
	
	/**
	 * @method is used for adding number of days in provided date
	 * @param date
	 * @param noOfDays
	 * @return
	 */
	public static Date addNoOfDaysInDate(Date date,int noOfDays) {
		System.out.println("Entering addNoOfDaysInCurrentDate");
		Calendar c = Calendar.getInstance(); 
		c.setTime(date); 
		c.add(Calendar.DATE, noOfDays);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar. MINUTE, 59);
		c.set(Calendar. SECOND, 59);
		date = c.getTime();
		System.out.println("Existing addNoOfDaysInCurrentDate");
		return date;
	}
	
	/**
	 * @method is used for adding number of months in provided date
	 * @param date
	 * @param noOfMonths
	 * @return
	 */
	public static Date addNoOfMonthsInDate(Date date,int noOfMonths) {
		System.out.println("Entering addNoOfDaysInCurrentDate");
		Calendar c = Calendar.getInstance(); 
		c.setTime(date); 
		c.add(Calendar.MONTH, noOfMonths);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar. MINUTE, 59);
		c.set(Calendar. SECOND, 59);
		date = c.getTime();
		System.out.println("Existing addNoOfDaysInCurrentDate");
		return date;
	}
}
