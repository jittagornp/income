/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.service.impl;

import com.pamarin.income.model.User;
import com.pamarin.income.repository.IncomeItemRepo;
import com.pamarin.income.service.SumaryIncomeService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author anonymous
 */
@Service
@Transactional
public class SumaryIncomeServiceImpl implements SumaryIncomeService {

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

}
