/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.controller;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author anonymous
 */
public abstract class AbstractBetweenExpensesItemCtrl {

    private static final String START2END = "start2end";
    private static final String BACK = "back";

    @Autowired
    private ExpensesItemCtrl itemCtrl;
    private Date date;
    private String type;
    //
    private Date firstDate;
    private Date lastDate;

    protected abstract Date toFirstDate(Date date);

    protected abstract Date toLastDate(Date date);

    protected abstract Date toBackDate(Date date);

    protected abstract Date toEndTime(Date date);

    public void reset() {
        type = START2END;
        date = null;
        search(date);
    }

    public String getSTART2END() {
        return START2END;
    }

    public String getBACK() {
        return BACK;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private void search(Date date) {
        if (START2END.equals(type)) {
            firstDate = toFirstDate(date);
            lastDate = toLastDate(date);
        } else {
            firstDate = toBackDate(date);
            lastDate = toEndTime(date);
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
