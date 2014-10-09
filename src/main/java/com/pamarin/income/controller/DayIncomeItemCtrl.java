/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.controller;

import com.pamarin.income.util.DateUtils;
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
    private Date date;
    private Date startTime;
    private Date endTime;

    public void reset() {
        search(null);
        date = null;
    }

    private void search(Date date) {
        startTime = DateUtils.toStartTime(date);
        endTime = DateUtils.toEndTime(date);

        itemCtrl.setStartDate(startTime);
        itemCtrl.setEndDate(endTime);
        itemCtrl.onSeach();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void onSearch() {
        search(date);
    }

    public void onClear() {
        reset();
    }
}
