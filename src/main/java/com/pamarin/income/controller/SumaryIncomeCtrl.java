/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.controller;

import com.pamarin.income.model.User;
import com.pamarin.income.security.SecurityUtils;
import com.pamarin.income.service.SumaryIncomeService;
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
public class SumaryIncomeCtrl {

    private Double sumary;
    private Long totalItems;
    @Autowired
    private SumaryIncomeService sumaryService;

    public void sumary(Date startDate, Date endDate) {
        User user = SecurityUtils.getUser();
        sumary = sumaryService.sumByOwnerAndBetweenIncomeDate(
                user,
                startDate,
                endDate
        );
        
        totalItems = sumaryService.countByOwnerAndBetweenIncomeDate(
                user, 
                startDate, 
                endDate
        );
    }

    public Double getSumary() {
        return sumary;
    }

    public Long getTotalItems() {
        return totalItems;
    }

}
