/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.service.impl;

import com.pamarin.income.model.User;
import com.pamarin.income.repository.IncomeItemRepo;
import com.pamarin.income.service.SummaryIncomeService;
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
public class SummaryIncomeServiceImpl implements SummaryIncomeService {

    @Autowired
    private IncomeItemRepo incomeItemRepo;

    @Override
    public Double sumByOwnerAndBetweenIncomeDate(User user, Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            return incomeItemRepo.sumByOwner(user);
        }

        return incomeItemRepo.sumByOwnerAndBetweenIncomeDate(user, startDate, endDate);
    }

    @Override
    public Long countByOwnerAndBetweenIncomeDate(User user, Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            return incomeItemRepo.countByOwner(user);
        }

        return incomeItemRepo.countByOwnerAndBetweenIncomeDate(user, startDate, endDate);
    }

    @Override
    public Date findMaxIncomeDateByOwner(User user, Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            return incomeItemRepo.findMaxIncomeDateByOwner(user);
        }

        return incomeItemRepo.findMaxIncomeDateByOwner(user, startDate, endDate);
    }

    @Override
    public Date findMixIncomeDateByOwner(User user, Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            return incomeItemRepo.findMinIncomeDateByOwner(user);
        }

        return incomeItemRepo.findMinIncomeDateByOwner(user, startDate, endDate);
    }

}
