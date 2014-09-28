/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pamarin.money.controller;

import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author anonymous
 */
@Component
@Scope("view")
public class TopicIncomeCtrl {
    
    private static final Logger LOG = LoggerFactory.getLogger(TopicIncomeCtrl.class);
    
    @PostConstruct
    public void postContruct(){
        LOG.debug("reset " + getClass().getName());
    }
    
    public String say(){
        return "hello world";
    }
}
