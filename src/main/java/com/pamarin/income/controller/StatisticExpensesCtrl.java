/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.controller;

import com.pamarin.income.model.Statistic;
import com.pamarin.income.model.User;
import com.pamarin.income.security.SecurityUtils;
import com.pamarin.income.service.StatisticExpensesService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author jittagornp
 */
@Component
@Scope("view")
public class StatisticExpensesCtrl {

    private Statistic maxItem;
    private Statistic minItem;
    private Statistic maxItemGroup;
    private Statistic minItemGroup;
    private Statistic maxItemTag;
    private Statistic minItemTag;

    @Autowired
    private StatisticExpensesService service;

    public void process(Date startDate, Date endDate) {
        User user = SecurityUtils.getUser();
        maxItem = service.findMaxItemByOwnerAndBetweenExpensesDate(
                user,
                startDate,
                endDate
        );

        minItem = service.findMinItemByOwnerAndBetweenExpensesDate(
                user,
                startDate,
                endDate
        );

        maxItemGroup = service.findMaxItemGroupByOwnerAndBetweenExpensesDate(
                user,
                startDate,
                endDate
        );

        minItemGroup = service.findMinItemGroupByOwnerAndBetweenExpensesDate(
                user,
                startDate,
                endDate
        );

        maxItemTag = service.findMaxItemTagByOwnerAndBetweenExpensesDate(
                user,
                startDate,
                endDate
        );

        minItemTag = service.findMinItemTagByOwnerAndBetweenExpensesDate(
                user,
                startDate,
                endDate
        );
    }

    public Statistic getMaxItem() {
        return maxItem;
    }

    public Statistic getMinItem() {
        return minItem;
    }

    public Statistic getMaxItemGroup() {
        return maxItemGroup;
    }

    public Statistic getMinItemGroup() {
        return minItemGroup;
    }

    public Statistic getMaxItemTag() {
        return maxItemTag;
    }

    public Statistic getMinItemTag() {
        return minItemTag;
    }

}
