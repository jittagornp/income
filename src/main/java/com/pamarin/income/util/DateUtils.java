/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.util;

import java.util.Calendar;
import java.util.Date;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

/**
 *
 * @author anonymous
 */
public class DateUtils {

    private static Date toTime(Date date, int hour, int minute, int second) {
        Calendar instance = Calendar.getInstance();
        if (date != null) {
            instance.setTime(date);
        }

        instance.set(Calendar.HOUR, hour);
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

    public static Date toFirstDateOfWeek(Date date) {
        if (date == null) {
            date = new Date();
        }

        LocalDate now = new LocalDate(date);
        return toStartTime(
                now.withDayOfWeek(
                        DateTimeConstants.MONDAY
                ).toDate()
        );
    }

    public static Date toLastDateOfWeek(Date date) {
        if (date == null) {
            date = new Date();
        }

        LocalDate now = new LocalDate(date);
        return toEndTime(
                now.withDayOfWeek(
                        DateTimeConstants.SUNDAY
                ).toDate()
        );
    }
}
