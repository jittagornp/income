/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.util;

import java.util.Calendar;
import java.util.Date;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

/**
 *
 * @author anonymous
 */
public class DateUtils {

    private static Date filterDate(Date date) {
        if (date != null) {
            return date;
        }

        return new Date();
    }

    private static Date toTime(Date date, int hour, int minute, int second) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(filterDate(date));
        instance.set(Calendar.HOUR_OF_DAY, hour);
        instance.set(Calendar.MINUTE, minute);
        instance.set(Calendar.SECOND, second);

        return instance.getTime();
    }

    public static Date toStartTime(Date date) {
        return toTime(date, 0, 0, 0);
    }

    public static Date toEndTime(Date date) {
        return toTime(date, 23, 59, 0);
    }

    private static Date toDateOfWeek(Date date, int index) {
        LocalDate now = new LocalDate(filterDate(date));
        return toStartTime(
                now.withDayOfWeek(index).toDate()
        );
    }

    public static Date toFirstDateOfMonth(Date date) {
        return new DateTime(filterDate(date).getTime())
                .dayOfMonth()
                .withMinimumValue()
                .toDate();
    }

    public static Date toLastDateOfMonth(Date date) {
        return new DateTime(filterDate(date).getTime())
                .dayOfMonth()
                .withMaximumValue()
                .toDate();
    }

    public static Date toFirstDateOfYear(Date date) {
        return new DateTime(filterDate(date).getTime())
                .dayOfYear()
                .withMinimumValue()
                .toDate();
    }

    public static Date toLastDateOfYear(Date date) {
        return new DateTime(filterDate(date).getTime())
                .dayOfYear()
                .withMaximumValue()
                .toDate();
    }

    public static Date toFirstDateOfWeek(Date date) {
        return toDateOfWeek(date, DateTimeConstants.MONDAY);
    }

    public static Date toLastDateOfWeek(Date date) {
        return toDateOfWeek(date, DateTimeConstants.SUNDAY);
    }

    /**
     * @param date
     * @param minusDate
     * @return 
     */
    public static Date minusDate(Date date, int minusDate) {
        DateTime dateTime = new DateTime(filterDate(date).getTime());
        DateTime minusDays = dateTime.minusDays(minusDate);
        return toStartTime(minusDays.toDate());
    }

    public static Date mergeDateTime(Date date, Date time) {
        Calendar dateC = Calendar.getInstance();
        Calendar timeC = Calendar.getInstance();

        dateC.setTime(date);
        timeC.setTime(time);

        dateC.set(Calendar.HOUR_OF_DAY, timeC.get(Calendar.HOUR_OF_DAY));
        dateC.set(Calendar.MINUTE, timeC.get(Calendar.MINUTE));
        dateC.set(Calendar.SECOND, timeC.get(Calendar.SECOND));

        return dateC.getTime();
    }

    /**
     * @param dayOfMonth start with 1
     * @param month start with 01
     * @param year
     * @param hourOfDay 0 - 23
     * @param minute start with 0
     * @param second start with 0
     * @return
     */
    public static Date buildDateTime(
            int dayOfMonth,
            int month,
            int year,
            int hourOfDay,
            int minute,
            int second
    ) { 
        Calendar dateC = Calendar.getInstance();

        dateC.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        dateC.set(Calendar.MONTH, month - 1); //january start with zero
        dateC.set(Calendar.YEAR, year);
        dateC.set(Calendar.HOUR_OF_DAY, hourOfDay);
        dateC.set(Calendar.MINUTE, minute);
        dateC.set(Calendar.SECOND, second);

        return dateC.getTime();
    }

    public static Date buildDate(int dayOfMonth, int month, int year) {
        return buildDateTime(dayOfMonth, month, year, 0, 0, 0);
    }

    public static Date buildTime(int hourOfDay, int minute, int second) {
        return buildDateTime(0, 1, 0, hourOfDay, minute, second);
    }
}
