/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.controller;

import java.util.Calendar;
import java.util.Date;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author anonymous
 */
@Component
@Scope("view")
public class WeekIncomeItemCtrl {

    @Autowired
    private IncomeItemCtrl itemCtrl;
    private Date startDate;

    public void reset() {
        search(null);
        startDate = null;
    }

    private Date findFirstDateOfWeek(Calendar instance, LocalDate now) {
        instance.setTime(now.withDayOfWeek(DateTimeConstants.MONDAY).toDate());
        instance.set(Calendar.HOUR, 0);
        instance.set(Calendar.MINUTE, 0);
        instance.set(Calendar.SECOND, 0);

        return instance.getTime();
    }

    private Date findLastDateWeek(Calendar instance, LocalDate now) {
        instance.setTime(now.withDayOfWeek(DateTimeConstants.SUNDAY).toDate());
        instance.set(Calendar.HOUR, 23);
        instance.set(Calendar.MINUTE, 59);
        instance.set(Calendar.SECOND, 0);

        return instance.getTime();
    }

    private void search(Date date) {
        if (date == null) {
            date = new Date();
        }

        Calendar instance = Calendar.getInstance();
        LocalDate now = new LocalDate(date);
        itemCtrl.setStartDate(findFirstDateOfWeek(instance, now));
        itemCtrl.setEndDate(findLastDateWeek(instance, now));
        itemCtrl.onSeach();
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void onSearch() {
        search(startDate);
    }

    public void onClear() {
        reset();
    }
}
