package com.myweb.mongo_anime.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    public static final DateTimeFormatter FORMAT_YYYY_MM_DD = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter FORMAT_DD_MM_YYYY = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter FORMAT_YYYY_MM_DD_HH_MM_SS = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
    public static final DateTimeFormatter FORMAT_YYYY_MM_DD_T_HH_MM = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    // Return: year
    public static int getYear(String dateStr, DateTimeFormatter inputFormat) {
        LocalDate date = LocalDate.parse(dateStr, inputFormat);
        return date.getYear();
    }

    // Return: format date
    public static String formatDate(String dateStr, DateTimeFormatter inputFormat, DateTimeFormatter outputFormat) {
        LocalDate date = LocalDate.parse(dateStr, inputFormat);
        return date.format(outputFormat);
    }

    // Input: today
    // Output: format date
    public static String formatDateToday(DateTimeFormatter outputFormat) {
        LocalDate date = LocalDate.now();
        return date.format(outputFormat);
    }

    // Return: Mon Tue Wed
    public static String getDayShort(String dateStr, DateTimeFormatter inputFormat) {
        LocalDate date = LocalDate.parse(dateStr, inputFormat);

        return date.getDayOfWeek()
                .getDisplayName(java.time.format.TextStyle.SHORT, Locale.CHINA);
    }

    // Return: Monday Tuesday
    public static String getDayFull(String dateStr, DateTimeFormatter inputFormat) {
        LocalDate date = LocalDate.parse(dateStr, inputFormat);

        return date.getDayOfWeek()
                .getDisplayName(java.time.format.TextStyle.FULL, Locale.CHINA);
    }
}
