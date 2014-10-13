/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.service;

import com.pamarin.income.model.User;
import java.util.Date;

/**
 *
 * @author jittagornp
 */
public interface SummaryExpensesService {

    Double sumByOwnerAndBetweenExpensesDate(User user, Date startDate, Date endDate);

    Long countByOwnerAndBetweenExpensesDate(User user, Date startDate, Date endDate);

    Date findMaxExpensesDateByOwner(User user, Date startDate, Date endDate);
    
    Date findMixExpensesDateByOwner(User user, Date startDate, Date endDate);
}
