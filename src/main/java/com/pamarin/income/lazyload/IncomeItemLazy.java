/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.lazyload;

import com.pamarin.income.model.IncomeItem;
import com.pamarin.income.security.SecurityUtils;
import com.pamarin.income.service.IncomeItemService;
import com.pamarin.income.util.SpringUtils;
import java.util.Date;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author jittagornp
 */
public class IncomeItemLazy extends LazyLoad<IncomeItem> {

    private final IncomeItemService itemService;
    private Date startDate;
    private Date endDate;

    public IncomeItemLazy() {
        this(null, null);
    }

    public IncomeItemLazy(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.itemService = SpringUtils.getBean(IncomeItemService.class);
    }

    @Override
    public Page<IncomeItem> load(Pageable page) {
        return itemService.findByOwnerAndBetweenIncomeDate(
                SecurityUtils.getUser(),
                startDate,
                endDate,
                page
        );
    }

}
