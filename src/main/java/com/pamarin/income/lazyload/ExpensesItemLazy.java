/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.lazyload;

import com.pamarin.income.model.ExpensesItem;
import com.pamarin.income.security.SecurityUtils;
import com.pamarin.income.service.ExpensesItemService;
import com.pamarin.income.util.SpringUtils;
import java.util.Date;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author jittagornp
 */
public class ExpensesItemLazy extends LazyLoad<ExpensesItem> {

    private final ExpensesItemService itemService;
    private Date startDate;
    private Date endDate;

    public ExpensesItemLazy() {
        this(null, null);
    }

    public ExpensesItemLazy(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.itemService = SpringUtils.getBean(ExpensesItemService.class);
    }

    @Override
    public Page<ExpensesItem> load(Pageable page) {
        return itemService.findByOwnerAndBetweenExpensesDate(
                SecurityUtils.getUser(),
                startDate,
                endDate,
                page
        );
    }

}
