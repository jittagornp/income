/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.lazyload;

import com.pamarin.income.lazyload.LazyLoad;
import com.pamarin.income.model.TopicIncome;
import com.pamarin.income.service.TopicIncomeService;
import com.pamarin.income.util.SpringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author jittagornp
 */
public class TopicIncomeLazy extends LazyLoad<TopicIncome> {

    private final TopicIncomeService service;

    public TopicIncomeLazy() {
        this.service = SpringUtils.getBean(TopicIncomeService.class);
    }

    @Override
    public Page<TopicIncome> load(Pageable page) {
        return service.findAll(page);
    }

}
