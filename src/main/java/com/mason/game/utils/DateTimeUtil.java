package com.mason.game.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Created by mwu on 2020/7/1
 */
public class DateTimeUtil {

    DateTimeFormatter STD_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    DateTimeFormatter SIMPLE_STD_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    DateTimeFormatter SOUGUO_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    DateTimeFormatter YYMMDD_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    DateTimeFormatter HHMMSS_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("HHmmss");
    DateTimeFormatter MINUTE_STD_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    DateTimeFormatter WORLD_OPEN_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss");

    static final long MINIMAL_DAYS_IN_FIRST_WEEK = 7;
    static final long DAY_TO_HOURS = 24L;
    static final long HOUR_TO_MINUTES = 60L;
    static final long MINUTE_TO_SECONDS = 60L;
    static final long HOUR_TO_SECONDS = HOUR_TO_MINUTES * MINUTE_TO_SECONDS;
    static final long DAY_TO_SECONDS = DAY_TO_HOURS * HOUR_TO_SECONDS;
    static final long WEEK_TO_SECONDS = MINIMAL_DAYS_IN_FIRST_WEEK * DAY_TO_SECONDS;
    static final long DAY_TO_MILLIS = DAY_TO_SECONDS * 1000L;

    public static Instant transfer(String dateStr, DateTimeFormatter formatter) {
        LocalDateTime time = LocalDateTime.parse(dateStr, formatter);
        return time.atZone(ZoneId.systemDefault()).toInstant();
    }
}
