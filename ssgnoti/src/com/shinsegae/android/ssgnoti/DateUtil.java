package com.shinsegae.android.ssgnoti;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static int getDDay(String d1) {
		long factor = 24 * 60 * 60 * 1000;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		SimpleDateFormat sdf_yyyy = new SimpleDateFormat("yyyy");
		int dday = 0;
		try {
			Date date1 = sdf.parse(sdf_yyyy.format(new Date())+d1.substring(4));
			Date today = sdf.parse(sdf.format(new Date()));
			long diff = date1.getTime() - today.getTime();
			dday = (int)(diff / factor);
			if (dday < 0) {
				dday += 365;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dday;
	}
}
