/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.repository;

import com.pamarin.income.model.ExpensesItem;
import com.pamarin.income.model.Statistic;
import com.pamarin.income.model.User;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author jittagornp
 */
public interface ExpensesItemRepo extends JpaRepository<ExpensesItem, Integer> {

    @Query(
            "SELECT item "
            + "FROM ExpensesItem item "
            + "WHERE item.owner = ?1 "
            + "AND (item.expensesDate BETWEEN ?2 AND ?3)"
    )
    public Page<ExpensesItem> findByOwnerAndBetweenExpensesDate(
            User user,
            Date startDate,
            Date endDate,
            Pageable page
    );

    @Query(
            "SELECT item "
            + "FROM ExpensesItem item "
            + "WHERE item.owner = ?1"
    )
    public Page<ExpensesItem> findByOwner(User user, Pageable page);

    public ExpensesItem findByIdAndOwner(Integer id, User user);

    @Query("SELECT SUM(item.expensesValue) "
            + "FROM ExpensesItem item "
            + "WHERE item.owner = ?1"
    )
    public Double sumByOwner(User user);

    @Query(
            "SELECT SUM(item.expensesValue) "
            + "FROM ExpensesItem item "
            + "WHERE item.owner = ?1 "
            + "AND (item.expensesDate BETWEEN ?2 AND ?3)"
    )
    public Double sumByOwnerAndBetweenExpensesDate(
            User user,
            Date startDate,
            Date endDate
    );

    @Query("SELECT COUNT(item.expensesValue) "
            + "FROM ExpensesItem item "
            + "WHERE item.owner = ?1"
    )
    public long countByOwner(User user);

    @Query(
            "SELECT COUNT(item.expensesValue) "
            + "FROM ExpensesItem item "
            + "WHERE item.owner = ?1 "
            + "AND (item.expensesDate BETWEEN ?2 AND ?3)"
    )
    public long countByOwnerAndBetweenExpensesDate(
            User user,
            Date startDate,
            Date endDate
    );

    @Query("SELECT MAX(item.expensesDate) "
            + "FROM ExpensesItem item "
            + "WHERE item.owner = ?1"
    )
    public Date findMaxExpensesDateByOwner(User user);

    @Query("SELECT MIN(item.expensesDate) "
            + "FROM ExpensesItem item "
            + "WHERE item.owner = ?1"
    )
    public Date findMinExpensesDateByOwner(User user);

    @Query("SELECT MAX(item.expensesDate) "
            + "FROM ExpensesItem item "
            + "WHERE item.owner = ?1 "
            + "AND (item.expensesDate BETWEEN ?2 AND ?3)"
    )
    public Date findMaxExpensesDateByOwner(
            User user,
            Date startDate,
            Date endDate
    );

    @Query("SELECT MIN(item.expensesDate) "
            + "FROM ExpensesItem item "
            + "WHERE item.owner = ?1 "
            + "AND (item.expensesDate BETWEEN ?2 AND ?3)"
    )
    public Date findMinExpensesDateByOwner(
            User user,
            Date startDate,
            Date endDate
    );

    @Query(
            "SELECT new com.pamarin.income.model.Statistic(item.expensesName, item.expensesValue) "
            + "FROM ExpensesItem item "
            + "WHERE item.owner = ?1"
    )
    public Page<Statistic> findItemByOwner(User user, Pageable page);

    @Query(
            "SELECT new com.pamarin.income.model.Statistic(item.expensesName, item.expensesValue) "
            + "FROM ExpensesItem item "
            + "WHERE item.owner = ?1 "
            + "AND (item.expensesDate BETWEEN ?2 AND ?3)"
    )
    public Page<Statistic> findItemByOwnerAndBetweenExpensesDate(
            User user,
            Date startDate,
            Date endDate,
            Pageable page
    );

    @Query(
            "SELECT item "
            + "FROM ExpensesItem item "
            + "WHERE item.owner = ?1 "
            + "ORDER BY item.expensesDate, item.expensesTime"
    )
    public List<ExpensesItem> findByOwner(User user);

    @Query(
            "SELECT item "
            + "FROM ExpensesItem item "
            + "WHERE item.owner = ?1 "
            + "AND (item.expensesDate BETWEEN ?2 AND ?3) "
            + "ORDER BY item.expensesDate, item.expensesTime"
    )
    public List<ExpensesItem> findByOwnerAndBetweenExpensesDate(
            User user,
            Date startDate,
            Date endDate
    );
}
