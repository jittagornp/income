/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.money.controller;

import com.pamarin.money.lazyload.LazyLoad;
import com.pamarin.money.model.IncomeItem;
import com.pamarin.money.service.IncomeItemService;
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
public class IncomeItemCtrl {

    private static final Logger LOG = LoggerFactory.getLogger(IncomeItemCtrl.class);
    @Autowired
    private IncomeItemService service;
    private IncomeItemCtrlLazy lazy;
    private IncomeItem item;

    @PostConstruct
    public void postConstruct() {
        lazy = new IncomeItemCtrlLazy();
    }

    public IncomeItemCtrlLazy getLazy() {
        return lazy;
    }

    public IncomeItem getItem() {
        return item;
    }

    public void setItem(IncomeItem item) {
        this.item = item;
    }

    public void onCreateItem() {
        item = new IncomeItem();
    }

    public void onAddItem() {
        service.save(item);
    }

    public class IncomeItemCtrlLazy extends LazyLoad<IncomeItem> {

        @Override
        public Page<IncomeItem> load(Pageable page) {
            return service.findAll(page);
        }

    }
}
