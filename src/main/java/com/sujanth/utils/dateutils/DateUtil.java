package com.sujanth.utils.dateutils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class DateUtil {
	
	public static String covertBetweenTimezones(String sourceDateTime, String sourceTimeZone, String targetTimeZone) {
		
		DateTimeFormatter sourceDateTimeFormatter = new DateTimeFormatterBuilder()
				.appendPattern("yyyy-MM-dd HH:mm:ss") //24-hour format
				.optionalStart()
				.appendFraction(ChronoField.MICRO_OF_SECOND,1,6,true)
				.optionalEnd()
				.toFormatter();
		
		LocalDateTime dateTime = LocalDateTime.parse(sourceDateTime, sourceDateTimeFormatter);
		
		ZoneId fromTimeZone = ZoneId.of(sourceTimeZone); //Source time-zone ID
		ZoneId toTimeZone = ZoneId.of(targetTimeZone);   //Target time-zone ID
		
		ZonedDateTime toUTCDateTime = dateTime.atZone(fromTimeZone);                    //source dateTime
		ZonedDateTime toTargetDateTime = toUTCDateTime.withZoneSameInstant(toTimeZone); //target dateTime
		
		DateTimeFormatter targetDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a z"); //12-hour format
		System.out.println(targetDateTimeFormatter.format(toTargetDateTime));
		return targetDateTimeFormatter.format(toTargetDateTime);
	}

	public static void main(String[] args) {
		
		DateUtil.covertBetweenTimezones("2021-05-25 20:10:35.56778", "America/Detroit", "America/Chicago");

	}

}
