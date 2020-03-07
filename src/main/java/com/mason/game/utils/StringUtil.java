package com.mason.game.utils;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

/**
 * Created by mwu on 2019/12/25
 * 字符串处理工具
 */
public class StringUtil {

    private static String baseString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private static String DATE_FORMAT_HOUR_MINUTE_SECOND = "yyyy-MM-dd HH:mm:ss";

    public static int toInt(String string) {
        return toDecimal(string).intValue();
    }

    public static long toLong(String string) {
        return toDecimal(string).longValue();
    }

    public static BigDecimal toDecimal(String string) {
        try {
            return new BigDecimal(string);
        } catch (Exception e) {
            return new BigDecimal(0);
        }
    }

    public static Date toDate(String string, String format) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            LocalDateTime dateTime = LocalDateTime.parse(string, formatter);
            ZoneId zoneId = ZoneId.systemDefault();
            ZonedDateTime zdt = dateTime.atZone(zoneId);
            return Date.from(zdt.toInstant());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("日期格式参数错误：" + format);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("日期输入参数错误：" + string);
        }
    }

    public static Date toDate(String string) {
        return toDate(string, DATE_FORMAT_HOUR_MINUTE_SECOND);
    }

    public static String toString(Date date, String format) {
        try {
            Instant instant = date.toInstant();
            ZoneId zoneId = ZoneId.systemDefault();
            LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            return localDateTime.format(formatter);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("日期格式参数错误：" + format);
        }
    }

    public static String toString(Date date) {
        return toString(date, DATE_FORMAT_HOUR_MINUTE_SECOND);
    }

    public static String random(int len) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(randomBaseString());
        }
        return sb.toString();
    }

    private static Character randomBaseString() {
        return baseString.charAt(RandomUtil.random(baseString.length()));
    }

    public static void main(String[] args) {
        System.out.println(toDate("123"));
    }
}
