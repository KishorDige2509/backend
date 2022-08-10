package com.dateTime;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateTimeRunner {
	public static void main(String[] args) {
		LocalDate now = LocalDate.now();
		LocalDate oneYearBefore = now.minusYears(1);
		
		Period period = Period.between(now, oneYearBefore);
		
		Date nowDate = new Date();
		
		Long hours = 69L;
		Long sec = 203L;
		Long millisec = 1000L;
		
		Long prevDate = nowDate.getTime() - (nowDate.getTime()-(millisec * sec));
		
		System.out.println("Seconds:" + TimeUnit.MILLISECONDS.toSeconds(prevDate));
		System.out.println("Minutes:" + TimeUnit.MILLISECONDS.toMinutes(prevDate));
		System.out.println("Hours:" + TimeUnit.MILLISECONDS.toHours(prevDate));
		
		System.out.println("Minutes converted:" + TimeUnit.MINUTES.convert(prevDate, TimeUnit.MILLISECONDS));
		
	}
}
