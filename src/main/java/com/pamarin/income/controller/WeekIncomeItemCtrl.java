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
    //
    private Date firstDate;
    private Date lastDate;

    public void reset() {
        type = MONDAY2SUNDAY;
        search(null);
        date = null;
    }

    public String getMONDAY2SUNDAY() {
        return MONDAY2SUNDAY;
    }

    public String getMINUS7DAY() {
        return MINUS7DAY;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private void search(Date date) {
        if (MONDAY2SUNDAY.equals(type)) {
            firstDate = DateUtils.toFirstDateOfWeek(date);
            lastDate = DateUtils.toLastDateOfWeek(date);
        } else {
            firstDate = DateUtils.minusDate(date, 6);
            lastDate = DateUtils.toEndTime(date);
        }

        itemCtrl.setStartDate(firstDate);
        itemCtrl.setEndDate(lastDate);

        itemCtrl.onSeach();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getFirstDate() {
        return firstDate;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void onSearch() {
        search(date);
    }

    public void onClear() {
        reset();
    }
}
