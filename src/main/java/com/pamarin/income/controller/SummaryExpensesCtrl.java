/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.controller;

import com.pamarin.income.model.User;
import com.pamarin.income.security.SecurityUtils;
import com.pamarin.income.service.SummaryExpensesService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author jittagornp
 */
@Component
@Scope("view")
public class SummaryExpensesCtrl {

    private Double summary;
    private Long totalItems;
    private Date startDate;
    private Date endDate;

    @Autowired
    private SummaryExpensesService summaryService;

    public void summary(Date startDate, Date endDate) {
        User user = SecurityUtils.getUser();
        summary = summaryService.sumByOwnerAndBetweenExpensesDate(
                user,
                startDate,
                endDate
        );

        totalItems = summaryService.countByOwnerAndBetweenExpensesDate(
                user,
                startDate,
                endDate
        );

        this.startDate = summaryService.findMixExpensesDateByOwner(
                user,
                startDate,
                endDate
        );
        
        this.endDate = summaryService.findMaxExpensesDateByOwner(
                user,
                startDate,
                endDate
        );
    }

    public Double getSummary() {
        return summary;
    }

    public Long getTotalItems() {
        return totalItems;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

}
