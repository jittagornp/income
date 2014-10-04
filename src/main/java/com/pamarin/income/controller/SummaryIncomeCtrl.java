/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.controller;

import com.pamarin.income.model.User;
import com.pamarin.income.security.SecurityUtils;
import com.pamarin.income.service.SummaryIncomeService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author anonymous
 */
@Component
@Scope("view")
public class SummaryIncomeCtrl {

    private Double summary;
    private Long totalItems;
    @Autowired
    private SummaryIncomeService summaryService;

    public void summary(Date startDate, Date endDate) {
        User user = SecurityUtils.getUser();
        summary = summaryService.sumByOwnerAndBetweenIncomeDate(
                user,
                startDate,
                endDate
        );
        
        totalItems = summaryService.countByOwnerAndBetweenIncomeDate(
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

}
