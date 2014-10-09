/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.controller;

import com.pamarin.income.util.DateUtils;
import java.util.Date;
import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author anonymous
 */
@Component
@Scope("view")
public class CurrentCtrl {

    private Date date;
    private Date firstDateOfWeek;
    private Date lastDateOfWeek;

    @PostConstruct
    public void postContruct() {
        date = new Date();
        firstDateOfWeek = DateUtils.findFirstDateOfWeek(date);
        lastDateOfWeek = DateUtils.findLastDateOfWeek(date);
    }

    public Date getDate() {
        return date;
    }

    public Date getFirstDateOfWeek() {
        return firstDateOfWeek;
    }

    public Date getLastDateOfWeek() {
        return lastDateOfWeek;
    }

}
