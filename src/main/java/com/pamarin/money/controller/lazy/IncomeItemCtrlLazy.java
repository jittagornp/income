/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.money.controller.lazy;

import com.pamarin.money.lazyload.LazyLoad;
import com.pamarin.money.model.IncomeItem;
import com.pamarin.money.service.IncomeItemService;
import com.pamarin.money.util.SpringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author anonymous
 */
public class IncomeItemCtrlLazy extends LazyLoad<IncomeItem> {

    private final IncomeItemService itemService;

    public IncomeItemCtrlLazy() {
        this.itemService = SpringUtils.getBean(IncomeItemService.class);
    }

    @Override
    public Page<IncomeItem> load(Pageable page) {
        return itemService.findAll(page);
    }

}
