/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.money.controller;

import com.pamarin.money.lazyload.LazyLoad;
import com.pamarin.money.model.TopicIncome;
import com.pamarin.money.service.TopicIncomeService;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

/**
 *
 * @author anonymous
 */
@Component
@Scope("view")
public class TopicIncomeCtrl {

    private static final Logger LOG = LoggerFactory.getLogger(TopicIncomeCtrl.class);
    @Autowired
    private TopicIncomeService service;
    private TopicIncomeLazy lazy;

    @PostConstruct
    public void postContruct() {
        lazy = new TopicIncomeLazy();
    }

    public TopicIncomeLazy getLazy() {
        return lazy;
    }

    public class TopicIncomeLazy extends LazyLoad<TopicIncome> {

        @Override
        public Page<TopicIncome> load(Pageable page) {
            return service.findAll(page);
        }

    }
}
