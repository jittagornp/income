/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author anonymous
 */
@Component
@Scope("view")
public class AllExpensesItemCtrl {

    @Autowired
    private ExpensesItemCtrl itemCtrl;

    public void reset() {
        itemCtrl.setStartDate(null);
        itemCtrl.setEndDate(null);
        itemCtrl.onSeach();
    }
}
