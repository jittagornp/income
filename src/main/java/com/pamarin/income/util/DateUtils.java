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

    private static Date toDate(Date date) {
        if (date != null) {
            return date;
        }

        return new Date();
    }

    private static Date toTime(Date date, int hour, int minute, int second) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(toDate(date));
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
        LocalDate now = new LocalDate(toDate(date));
        return toStartTime(
                now.withDayOfWeek(index).toDate()
        );
    }

    public static Date toFirstDateOfMonth(Date date) {
        return new DateTime(toDate(date).getTime())
                .dayOfMonth()
                .withMinimumValue()
                .toDate();
    }

    public static Date toLastDateOfMonth(Date date) {
        return new DateTime(toDate(date).getTime())
                .dayOfMonth()
                .withMaximumValue()
                .toDate();
    }

    public static Date toFirstDateOfYear(Date date) {
        return new DateTime(toDate(date).getTime())
                .dayOfYear()
                .withMinimumValue()
                .toDate();
    }

    public static Date toLastDateOfYear(Date date) {
        return new DateTime(toDate(date).getTime())
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

    public static Date minusDate(Date date, int minus) {
        DateTime dateTime = new DateTime(toDate(date).getTime());
        DateTime minusDays = dateTime.minusDays(minus);
        return toStartTime(minusDays.toDate());
    }
}
