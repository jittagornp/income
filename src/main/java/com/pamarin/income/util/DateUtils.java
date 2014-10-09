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

    public static Date toStartTime(Date date) {
        Calendar instance = Calendar.getInstance();
        if (date != null) {
            instance.setTime(date);
        }

        instance.set(Calendar.HOUR, 0);
        instance.set(Calendar.MINUTE, 0);
        instance.set(Calendar.SECOND, 0);

        return instance.getTime();
    }

    public static Date toEndTime(Date date) {
        Calendar instance = Calendar.getInstance();
        if (date != null) {
            instance.setTime(date);
        }

        instance.set(Calendar.HOUR, 23);
        instance.set(Calendar.MINUTE, 59);
        instance.set(Calendar.SECOND, 0);

        return instance.getTime();
    }

    public static Date findFirstDateOfWeek(Date date) {
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

    public static Date findLastDateOfWeek(Date date) {
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
