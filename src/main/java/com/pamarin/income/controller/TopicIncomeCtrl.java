/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.controller;

import com.pamarin.income.lazyload.TopicIncomeLazy;
import com.pamarin.income.model.TopicIncome;
import com.pamarin.income.service.TopicIncomeService;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author jittagornp
 */
@Component
@Scope("view")
public class TopicIncomeCtrl {

    private static final Logger LOG = LoggerFactory.getLogger(TopicIncomeCtrl.class);
    @Autowired
    private TopicIncomeService service;
    private TopicIncomeLazy lazy;
    private TopicIncome topic;

    @PostConstruct
    public void postContruct() {
        lazy = new TopicIncomeLazy();
    }

    public TopicIncomeLazy getLazy() {
        return lazy;
    }

    public void onCreateTopic() {
        topic = new TopicIncome();
    }

    public TopicIncome getTopic() {
        return topic;
    }

    public void setTopic(TopicIncome topic) {
        this.topic = topic;
    }
    
    public void onAddTopic(){
        service.saveTopic(topic);
    }
}
