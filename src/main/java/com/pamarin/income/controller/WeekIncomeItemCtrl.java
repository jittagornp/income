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

    @Autowired
    private IncomeItemCtrl itemCtrl;
    private Date startDate;

    public void reset() {
        search(null);
        startDate = null;
    }

    private void search(Date date) {
        itemCtrl.setStartDate(DateUtils.findFirstDateOfWeek(date));
        itemCtrl.setEndDate(DateUtils.findLastDateOfWeek(date));
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
