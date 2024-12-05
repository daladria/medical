package com.magis.utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class DateUtility {

	public String getFirstDayOfNextMonth( String pattern){
		Calendar calendar = Calendar.getInstance();         
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		Date nextMonthFirstDay = calendar.getTime();
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(nextMonthFirstDay);

	}

	public String getEndDayOfNextMonth( String pattern){

		Calendar calendar = Calendar.getInstance();         
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date nextMonthFirstDay = calendar.getTime();
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(nextMonthFirstDay);
	}

	public static String getCurrentDay( String pattern){         
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(System.currentTimeMillis());

	}

	
	public static String ms2Date(String milisecond, String pattern){
		DateFormat formatter = new SimpleDateFormat(pattern);
		long milliSeconds= Long.parseLong(milisecond);
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(milliSeconds);
		return (formatter.format(calendar.getTime())); 
	}

	public static String ms2Date(Long miliSecond, String pattern){
		DateFormat formatter = new SimpleDateFormat(pattern);
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(miliSecond);
		return (formatter.format(calendar.getTime()));
		//return (Integer.parseInt(formatter.format(calendar.getTime()).replaceAll(":",""))); 
	}

	public int randInt(int min, int max) {

	    Random rand = new Random();

	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	public int datediff(String dateStart,String dateFormat){

		SimpleDateFormat format = new SimpleDateFormat(dateFormat);  

		Date d1 = null;
		Date d2 = new Date();
		try {
		    d1 = format.parse(dateStart);
		} catch (Exception e) {
		    e.printStackTrace();
		}    

		long diff = d2.getTime() - d1.getTime();
		long diffDays = diff / (24*60 * 60 * 1000); 
		return Integer.parseInt(""+diffDays);
	}
	
	public long date2Miliseconds(String date,String dateFormat){

		SimpleDateFormat format = new SimpleDateFormat(dateFormat);  

		Date d1 = null;

		try {
		    d1 = format.parse(date);
		} catch (Exception e) {
		    e.printStackTrace();
		}    

		return d1.getTime();
	}

	
	public static void main(String[] args) {
		DateUtility dt = new DateUtility();
		long t1 = System.currentTimeMillis();
		String strt1 = dt.ms2Date(t1, "yyyy-MM-dd HH:mm:ss");
		System.out.println(t1);
		System.out.println(strt1);
		t1 = dt.date2Miliseconds(strt1, "yyyy-MM-dd HH:mm:ss");
		System.out.println( t1);
		 strt1 = dt.ms2Date(t1, "yyyy-MM-dd HH:mm:ss");
		System.out.println(strt1);

		
	}
	
}
