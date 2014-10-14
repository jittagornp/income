/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.repository;

import com.pamarin.income.model.AutoIncomeItem;
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
public interface AutoIncomeItemRepo extends JpaRepository<AutoIncomeItem, Integer> {

    @Query(
            "SELECT item "
            + "FROM AutoIncomeItem item "
            + "WHERE item.owner = ?1 "
            + "AND (item.incomeDate BETWEEN ?2 AND ?3)"
    )
    public Page<AutoIncomeItem> findByOwnerAndBetweenIncomeDate(
            User user,
            Date startDate,
            Date endDate,
            Pageable page
    );

    @Query(
            "SELECT item "
            + "FROM AutoIncomeItem item "
            + "WHERE item.owner = ?1"
    )
    public Page<AutoIncomeItem> findByOwner(User user, Pageable page);

    public AutoIncomeItem findByIdAndOwner(Integer id, User user);

    @Query("SELECT SUM(item.incomeValue) "
            + "FROM AutoIncomeItem item "
            + "WHERE item.owner = ?1"
    )
    public Double sumByOwner(User user);

    @Query(
            "SELECT SUM(item.incomeValue) "
            + "FROM AutoIncomeItem item "
            + "WHERE item.owner = ?1 "
            + "AND (item.incomeDate BETWEEN ?2 AND ?3)"
    )
    public Double sumByOwnerAndBetweenIncomeDate(
            User user,
            Date startDate,
            Date endDate
    );

    @Query("SELECT COUNT(item.incomeValue) "
            + "FROM AutoIncomeItem item "
            + "WHERE item.owner = ?1"
    )
    public long countByOwner(User user);

    @Query(
            "SELECT COUNT(item.incomeValue) "
            + "FROM AutoIncomeItem item "
            + "WHERE item.owner = ?1 "
            + "AND (item.incomeDate BETWEEN ?2 AND ?3)"
    )
    public long countByOwnerAndBetweenIncomeDate(
            User user,
            Date startDate,
            Date endDate
    );

    @Query("SELECT MAX(item.incomeDate) "
            + "FROM AutoIncomeItem item "
            + "WHERE item.owner = ?1"
    )
    public Date findMaxIncomeDateByOwner(User user);

    @Query("SELECT MIN(item.incomeDate) "
            + "FROM AutoIncomeItem item "
            + "WHERE item.owner = ?1"
    )
    public Date findMinIncomeDateByOwner(User user);

    @Query("SELECT MAX(item.incomeDate) "
            + "FROM AutoIncomeItem item "
            + "WHERE item.owner = ?1 "
            + "AND (item.incomeDate BETWEEN ?2 AND ?3)"
    )
    public Date findMaxIncomeDateByOwner(
            User user,
            Date startDate,
            Date endDate
    );

    @Query("SELECT MIN(item.incomeDate) "
            + "FROM AutoIncomeItem item "
            + "WHERE item.owner = ?1 "
            + "AND (item.incomeDate BETWEEN ?2 AND ?3)"
    )
    public Date findMinIncomeDateByOwner(
            User user,
            Date startDate,
            Date endDate
    );

    @Query(
            "SELECT new com.pamarin.income.model.Statistic(item.incomeName, item.incomeValue) "
            + "FROM AutoIncomeItem item "
            + "WHERE item.owner = ?1"
    )
    public Page<Statistic> findItemByOwner(User user, Pageable page);

    @Query(
            "SELECT new com.pamarin.income.model.Statistic(item.incomeName, item.incomeValue) "
            + "FROM AutoIncomeItem item "
            + "WHERE item.owner = ?1 "
            + "AND (item.incomeDate BETWEEN ?2 AND ?3)"
    )
    public Page<Statistic> findItemByOwnerAndBetweenIncomeDate(
            User user,
            Date startDate,
            Date endDate,
            Pageable page
    );

    @Query(
            "SELECT item "
            + "FROM AutoIncomeItem item "
            + "WHERE item.owner = ?1 "
            + "ORDER BY item.incomeDate, item.incomeTime"
    )
    public List<AutoIncomeItem> findByOwner(User user);

    @Query(
            "SELECT item "
            + "FROM AutoIncomeItem item "
            + "WHERE item.owner = ?1 "
            + "AND (item.incomeDate BETWEEN ?2 AND ?3) "
            + "ORDER BY item.incomeDate, item.incomeTime"
    )
    public List<AutoIncomeItem> findByOwnerAndBetweenIncomeDate(
            User user,
            Date startDate,
            Date endDate
    );
}
