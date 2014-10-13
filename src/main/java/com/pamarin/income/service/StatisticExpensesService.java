/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.service;

import com.pamarin.income.model.Statistic;
import com.pamarin.income.model.User;
import java.util.Date;

/**
 *
 * @author jittagornp
 */
public interface StatisticExpensesService {

    Statistic findMaxItemByOwnerAndBetweenExpensesDate(User user, Date startDate, Date endDate);

    Statistic findMinItemByOwnerAndBetweenExpensesDate(User user, Date startDate, Date endDate);

    Statistic findMaxItemGroupByOwnerAndBetweenExpensesDate(User user, Date startDate, Date endDate);

    Statistic findMinItemGroupByOwnerAndBetweenExpensesDate(User user, Date startDate, Date endDate);

    Statistic findMaxItemTagByOwnerAndBetweenExpensesDate(User user, Date startDate, Date endDate);

    Statistic findMinItemTagByOwnerAndBetweenExpensesDate(User user, Date startDate, Date endDate);
}
