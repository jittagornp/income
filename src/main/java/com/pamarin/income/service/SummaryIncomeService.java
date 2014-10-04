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
 * @author anonymous
 */
public interface SummaryIncomeService {

    Double sumByOwnerAndBetweenIncomeDate(User user, Date startDate, Date endDate);
    
    Long countByOwnerAndBetweenIncomeDate(User user, Date startDate, Date endDate);
}
