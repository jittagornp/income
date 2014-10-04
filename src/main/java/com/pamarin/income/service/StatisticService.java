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
 * @author anonymous
 */
public interface StatisticService {
    
    Statistic findMaxItemByOwnerAndBetweenIncomeDate(User user, Date startDate, Date endDate);
    
    Statistic findMinItemByOwnerAndBetweenIncomeDate(User user, Date startDate, Date endDate);
}
