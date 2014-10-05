/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.service.impl;

import com.pamarin.income.model.Statistic;
import com.pamarin.income.model.User;
import com.pamarin.income.repository.IncomeItemRepo;
import com.pamarin.income.repository.NativeIncomeItemRepo;
import com.pamarin.income.service.StatisticService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author anonymous
 */
@Service
@Transactional
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    private IncomeItemRepo repo;
    @Autowired
    private NativeIncomeItemRepo nativeRepo;

    private Statistic findItemByOwnerAndBetweenIncomeDate(User user, Date startDate, Date endDate, Sort.Direction direction) {
        PageRequest request = new PageRequest(0, 1, direction, "incomeValue");
        Page<Statistic> result;
        if (startDate == null || endDate == null) {
            result = repo.findItemByOwner(user, request);
        } else {
            result = repo.findItemByOwnerAndBetweenIncomeDate(user, startDate, endDate, request);
        }

        if (result.getTotalElements() == 0) {
            return Statistic.EMPTY;
        }

        return result.getContent().get(0);
    }

    @Override
    public Statistic findMaxItemByOwnerAndBetweenIncomeDate(User user, Date startDate, Date endDate) {
        return findItemByOwnerAndBetweenIncomeDate(
                user,
                startDate,
                endDate,
                Sort.Direction.DESC
        );
    }

    @Override
    public Statistic findMinItemByOwnerAndBetweenIncomeDate(User user, Date startDate, Date endDate) {
        return findItemByOwnerAndBetweenIncomeDate(
                user,
                startDate,
                endDate,
                Sort.Direction.ASC
        );
    }

    @Override
    public Statistic findMaxItemGroupByOwnerAndBetweenIncomeDate(User user, Date startDate, Date endDate) {
        return nativeRepo.findGroupItemByOwner(
                user,
                "DESC",
                startDate,
                endDate
        );
    }

    @Override
    public Statistic findMinItemGroupByOwnerAndBetweenIncomeDate(User user, Date startDate, Date endDate) {
        return nativeRepo.findGroupItemByOwner(
                user,
                "ASC",
                startDate,
                endDate
        );
    }

}
