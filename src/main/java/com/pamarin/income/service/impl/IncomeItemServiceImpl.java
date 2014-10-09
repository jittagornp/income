/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.service.impl;

import com.pamarin.income.model.IncomeItem;
import com.pamarin.income.model.User;
import com.pamarin.income.repository.IncomeItemRepo;
import com.pamarin.income.service.IncomeItemService;
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
public class IncomeItemServiceImpl implements IncomeItemService {

    @Autowired
    private IncomeItemRepo repo;

    @Override
    public IncomeItem save(IncomeItem item) {
        return repo.save(item);
    }

    @Override
    public Page<IncomeItem> findByOwnerAndBetweenIncomeDate(
            User user,
            Date startDate,
            Date endDate,
            Pageable page
    ) {
        if (startDate == null || endDate == null) {
            return repo.findByOwner(user, page);
        }

        return repo.findByOwnerAndBetweenIncomeDate(user, startDate, endDate, page);
    }

    @Override
    public void delete(IncomeItem item) {
        if (item == null) {
            throw new NullPointerException("item is null.");
        }

        repo.delete(item.getId());
    }

    @Override
    public IncomeItem findByIdAndOwner(Integer id, User user) {
        return repo.findByIdAndOwner(id, user);
    }

    @Override
    public List<IncomeItem> findByOwnerAndBetweenIncomeDate(User user, Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            return repo.findByOwner(user);
        }

        return repo.findByOwnerAndBetweenIncomeDate(user, startDate, endDate);
    }

}
