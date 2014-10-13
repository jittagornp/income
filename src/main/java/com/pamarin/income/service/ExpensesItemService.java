/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.service;

import com.pamarin.income.model.ExpensesItem;
import com.pamarin.income.model.User;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author jittagornp
 */
public interface ExpensesItemService {

    ExpensesItem save(ExpensesItem item);

    Page<ExpensesItem> findByOwnerAndBetweenExpensesDate(
            User user,
            Date startDate,
            Date endDate,
            Pageable page
    );

    List<ExpensesItem> findByOwnerAndBetweenExpensesDate(
            User user,
            Date startDate,
            Date endDate
    );

    void delete(ExpensesItem item);

    ExpensesItem findByIdAndOwner(Integer id, User user);
}
