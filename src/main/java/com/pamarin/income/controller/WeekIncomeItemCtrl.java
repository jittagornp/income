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
public class WeekIncomeItemCtrl {

    private static final String MONDAY2SUNDAY = "monday2sunday";
    private static final String MINUS7DAY = "minus7day";

    @Autowired
    private IncomeItemCtrl itemCtrl;
    private Date date;
    private String type;

    public void reset() {
        search(null);
        date = null;
        type = MONDAY2SUNDAY;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private void search(Date date) {
        if (MONDAY2SUNDAY.equals(type)) {
            itemCtrl.setStartDate(DateUtils.toFirstDateOfWeek(date));
            itemCtrl.setEndDate(DateUtils.toLastDateOfWeek(date));
        } else {
            itemCtrl.setStartDate(DateUtils.minusDate(date, 7));
            itemCtrl.setEndDate(DateUtils.toEndTime(date));
        }

        itemCtrl.onSeach();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void onSearch() {
        search(date);
    }

    public void onClear() {
        reset();
    }
}
