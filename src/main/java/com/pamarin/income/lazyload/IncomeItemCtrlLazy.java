/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.lazyload;

import com.pamarin.income.lazyload.LazyLoad;
import com.pamarin.income.model.IncomeItem;
import com.pamarin.income.service.IncomeItemService;
import com.pamarin.income.util.SpringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author jittagornp
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
