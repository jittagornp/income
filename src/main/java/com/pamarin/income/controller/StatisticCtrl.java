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
    private Statistic maxItemGroup;
    private Statistic minItemGroup;
    private Statistic maxItemTag;
    private Statistic minItemTag;

    @Autowired
    private StatisticService service;

    public void process(Date startDate, Date endDate) {
        User user = SecurityUtils.getUser();
        maxItem = service.findMaxItemByOwnerAndBetweenIncomeDate(
                user,
                startDate,
                endDate
        );

        minItem = service.findMinItemByOwnerAndBetweenIncomeDate(
                user,
                startDate,
                endDate
        );

        maxItemGroup = service.findMaxItemGroupByOwnerAndBetweenIncomeDate(
                user,
                startDate,
                endDate
        );

        minItemGroup = service.findMinItemGroupByOwnerAndBetweenIncomeDate(
                user,
                startDate,
                endDate
        );

        maxItemTag = service.findMaxItemTagByOwnerAndBetweenIncomeDate(
                user,
                startDate,
                endDate
        );

        minItemTag = service.findMinItemTagByOwnerAndBetweenIncomeDate(
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
