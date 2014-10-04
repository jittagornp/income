/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.controller;

import com.pamarin.income.model.Statistic;
import com.pamarin.income.model.User;
import com.pamarin.income.security.SecurityUtils;
import com.pamarin.income.service.StatisticService;
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
public class StatisticCtrl {

    private Statistic maxItem;
    private Statistic minItem;
    @Autowired
    private StatisticService service;

    public void process(Date startDate, Date endDate) {
        User user = SecurityUtils.getUser();
        maxItem = service.findMaxItemByOwnerAndBetweenIncomeDate(user, startDate, endDate);
        minItem = service.findMinItemByOwnerAndBetweenIncomeDate(user, startDate, endDate);
    }

    public Statistic getMaxItem() {
        return maxItem;
    }

    public Statistic getMinItem() {
        return minItem;
    }

}
