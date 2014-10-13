/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.service.impl;

import com.pamarin.income.model.User;
import com.pamarin.income.repository.ExpensesItemRepo;
import com.pamarin.income.service.SummaryExpensesService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jittagornp
 */
@Service
@Transactional
public class SummaryExpensesServiceImpl implements SummaryExpensesService {

    @Autowired
    private ExpensesItemRepo expensesItemRepo;

    @Override
    public Double sumByOwnerAndBetweenExpensesDate(User user, Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            return expensesItemRepo.sumByOwner(user);
        }

        return expensesItemRepo.sumByOwnerAndBetweenExpensesDate(user, startDate, endDate);
    }

    @Override
    public Long countByOwnerAndBetweenExpensesDate(User user, Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            return expensesItemRepo.countByOwner(user);
        }

        return expensesItemRepo.countByOwnerAndBetweenExpensesDate(user, startDate, endDate);
    }

    @Override
    public Date findMaxExpensesDateByOwner(User user, Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            return expensesItemRepo.findMaxExpensesDateByOwner(user);
        }

        return expensesItemRepo.findMaxExpensesDateByOwner(user, startDate, endDate);
    }

    @Override
    public Date findMixExpensesDateByOwner(User user, Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            return expensesItemRepo.findMinExpensesDateByOwner(user);
        }

        return expensesItemRepo.findMinExpensesDateByOwner(user, startDate, endDate);
    }

}
