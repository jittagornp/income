/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.controller;

import com.pamarin.income.util.DateUtils;
import java.util.Date;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author anonymous
 */
@Component
@Scope("view")
public class DayIncomeItemCtrl extends AbstractBetweenIncomeItemCtrl {

    @Override
    protected Date toFirstDate(Date date) {
        return DateUtils.toStartTime(date);
    }

    @Override
    protected Date toLastDate(Date date) {
        return DateUtils.toEndTime(date);
    }

    @Override
    protected Date toBackDate(Date date) {
        return DateUtils.toStartTime(date);
    }

    @Override
    protected Date toEndTime(Date date) {
        return DateUtils.toEndTime(date);
    }
}
