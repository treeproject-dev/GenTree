package utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

public class DateConverters {
	
	public static boolean isCorrectDate(String strDate) {
		boolean answer = false;
		//if (strDate.equals(""))return false;
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
			answer = true;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return answer;
	}

	public static String dateToString(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = formatter.format(date);
		return strDate;
	}

	public static Date stringToDate(String strDate) {
		Date date;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
			date = new Date(0);
		}
		return date;

	}
}
