/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.service.impl;

import com.pamarin.income.model.Statistic;
import com.pamarin.income.model.User;
import com.pamarin.income.repository.ExpensesItemRepo;
import com.pamarin.income.repository.NativeExpensesItemRepo;
import com.pamarin.income.service.StatisticExpensesService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jittagornp
 */
@Service
@Transactional
public class StatisticExpensesServiceImpl implements StatisticExpensesService {

    @Autowired
    private ExpensesItemRepo repo;
    @Autowired
    private NativeExpensesItemRepo nativeRepo;

    private Statistic findItemByOwnerAndBetweenExpensesDate(User user, Date startDate, Date endDate, Sort.Direction direction) {
        PageRequest request = new PageRequest(0, 1, direction, "expensesValue");
        Page<Statistic> result;
        if (startDate == null || endDate == null) {
            result = repo.findItemByOwner(user, request);
        } else {
            result = repo.findItemByOwnerAndBetweenExpensesDate(user, startDate, endDate, request);
        }

        if (result.getTotalElements() == 0) {
            return Statistic.EMPTY;
        }

        return result.getContent().get(0);
    }

    @Override
    public Statistic findMaxItemByOwnerAndBetweenExpensesDate(User user, Date startDate, Date endDate) {
        return findItemByOwnerAndBetweenExpensesDate(
                user,
                startDate,
                endDate,
                Sort.Direction.DESC
        );
    }

    @Override
    public Statistic findMinItemByOwnerAndBetweenExpensesDate(User user, Date startDate, Date endDate) {
        return findItemByOwnerAndBetweenExpensesDate(
                user,
                startDate,
                endDate,
                Sort.Direction.ASC
        );
    }

    @Override
    public Statistic findMaxItemGroupByOwnerAndBetweenExpensesDate(User user, Date startDate, Date endDate) {
        return nativeRepo.findItemGroupByOwner(
                user,
                "DESC",
                startDate,
                endDate
        );
    }

    @Override
    public Statistic findMinItemGroupByOwnerAndBetweenExpensesDate(User user, Date startDate, Date endDate) {
        return nativeRepo.findItemGroupByOwner(
                user,
                "ASC",
                startDate,
                endDate
        );
    }

    @Override
    public Statistic findMaxItemTagByOwnerAndBetweenExpensesDate(User user, Date startDate, Date endDate) {
        return nativeRepo.findItemTagByOwner(
                user,
                "DESC",
                startDate,
                endDate
        );
    }

    @Override
    public Statistic findMinItemTagByOwnerAndBetweenExpensesDate(User user, Date startDate, Date endDate) {
        return nativeRepo.findItemTagByOwner(
                user,
                "ASC",
                startDate,
                endDate
        );
    }

}
