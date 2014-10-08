/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.controller;

import java.util.Calendar;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author anonymous
 */
@Component
@Scope("view")
public class DayIncomeItemCtrl {

    @Autowired
    private IncomeItemCtrl itemCtrl;
    private Date startDate;

    public void reset() {
        search(null);
        startDate = null;
    }

    private Date findStartTime(Calendar instance) {
        instance.set(Calendar.HOUR, 0);
        instance.set(Calendar.MINUTE, 0);
        instance.set(Calendar.SECOND, 0);

        return instance.getTime();
    }

    private Date findEndTime(Calendar instance) {
        instance.set(Calendar.HOUR, 23);
        instance.set(Calendar.MINUTE, 59);
        instance.set(Calendar.SECOND, 0);

        return instance.getTime();
    }

    private void search(Date date) {
        Calendar instance = Calendar.getInstance();
        if (date != null) {
            instance.setTime(date);
        }

        itemCtrl.setStartDate(findStartTime(instance));
        itemCtrl.setEndDate(findEndTime(instance));
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
