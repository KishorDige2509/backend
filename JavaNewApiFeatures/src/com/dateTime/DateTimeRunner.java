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
		
		Long prevDate = nowDate.getTime() - (nowDate.getTime()-(1000 * 60 * 60));
		
		System.out.println("Seconds:" + TimeUnit.MILLISECONDS.toSeconds(prevDate));
		System.out.println("Minutes:" + TimeUnit.MILLISECONDS.toMinutes(prevDate));
		System.out.println("Hours:" + TimeUnit.MILLISECONDS.toHours(prevDate));
		
		Integer nu = null;
		
		Integer sum = nu + 1;
		System.out.println("Sum:" + sum);
	}
}
