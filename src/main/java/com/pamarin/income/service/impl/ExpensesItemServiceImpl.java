/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.service.impl;

import com.pamarin.income.model.ExpensesItem;
import com.pamarin.income.model.User;
import com.pamarin.income.repository.ExpensesItemRepo;
import com.pamarin.income.service.ExpensesItemService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jittagornp
 */
@Service
@Transactional
public class ExpensesItemServiceImpl implements ExpensesItemService {

    @Autowired
    private ExpensesItemRepo repo;

    @Override
    public ExpensesItem save(ExpensesItem item) {
        return repo.save(item);
    }

    @Override
    public Page<ExpensesItem> findByOwnerAndBetweenExpensesDate(
            User user,
            Date startDate,
            Date endDate,
            Pageable page
    ) {
        if (startDate == null || endDate == null) {
            return repo.findByOwner(user, page);
        }

        return repo.findByOwnerAndBetweenExpensesDate(user, startDate, endDate, page);
    }

    @Override
    public void delete(ExpensesItem item) {
        if (item == null) {
            throw new NullPointerException("item is null.");
        }

        repo.delete(item.getId());
    }

    @Override
    public ExpensesItem findByIdAndOwner(Integer id, User user) {
        return repo.findByIdAndOwner(id, user);
    }

    @Override
    public List<ExpensesItem> findByOwnerAndBetweenExpensesDate(User user, Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            return repo.findByOwner(user);
        }

        return repo.findByOwnerAndBetweenExpensesDate(user, startDate, endDate);
    }

}
