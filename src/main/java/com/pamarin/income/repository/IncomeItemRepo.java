/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.repository;

import com.pamarin.income.model.IncomeItem;
import com.pamarin.income.model.User;
import java.util.Date;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author jittagornp
 */
public interface IncomeItemRepo extends JpaRepository<IncomeItem, Integer> {

    @Query("SELECT item FROM IncomeItem item WHERE item.owner = ?1 AND (item.incomeDate BETWEEN ?2 AND ?3)")
    public Page<IncomeItem> findByOwnerAndBetweenIncomeDate(User user, Date startDate, Date endDate, Pageable page);

    @Query("SELECT item FROM IncomeItem item WHERE item.owner = ?1")
    public Page<IncomeItem> findByOwner(User user, Pageable page);

}
