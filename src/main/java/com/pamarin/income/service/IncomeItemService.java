/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.service;

import com.pamarin.income.model.IncomeItem;
import com.pamarin.income.model.User;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author jittagornp
 */
public interface IncomeItemService {

    IncomeItem save(IncomeItem item);

    Page<IncomeItem> findByOwnerAndBetweenIncomeDate(
            User user,
            Date startDate,
            Date endDate,
            Pageable page
    );

    List<IncomeItem> findByOwnerAndBetweenIncomeDate(
            User user,
            Date startDate,
            Date endDate
    );

    void delete(IncomeItem item);

    IncomeItem findByIdAndOwner(Integer id, User user);
}
