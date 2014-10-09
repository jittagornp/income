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
public class YearIncomeItemCtrl {

    private static final String START2END = "start2end";
    private static final String MINUS365DAY = "minus365day";

    @Autowired
    private IncomeItemCtrl itemCtrl;
    private Date date;
    private String type;
    //
    private Date firstDate;
    private Date lastDate;

    public void reset() {
        type = START2END;
        search(null);
        date = null;
    }

    public String getSTART2END() {
        return START2END;
    }

    public String getMINUS365DAY() {
        return MINUS365DAY;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private void search(Date date) {
        if (START2END.equals(type)) {
            firstDate = DateUtils.toFirstDateOfYear(date);
            lastDate = DateUtils.toLastDateOfYear(date);
        } else {
            firstDate = DateUtils.minusDate(date, 364);
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
