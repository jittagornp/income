/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.controller;

import java.util.Calendar;
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

    public void reset() {
        Calendar instance = Calendar.getInstance();

        instance.set(Calendar.HOUR, 0);
        instance.set(Calendar.MINUTE, 0);
        instance.set(Calendar.SECOND, 0);
        itemCtrl.setStartDate(instance.getTime());

        instance.set(Calendar.HOUR, 23);
        instance.set(Calendar.MINUTE, 59);
        instance.set(Calendar.SECOND, 0);
        itemCtrl.setEndDate(instance.getTime());
        itemCtrl.onSeach();
    }
}
