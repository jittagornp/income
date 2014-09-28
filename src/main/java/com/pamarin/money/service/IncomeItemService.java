/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.money.service;

import com.pamarin.money.model.IncomeItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author jittagornp
 */
public interface IncomeItemService {

    public Page<IncomeItem> findAll(Pageable page);

    public IncomeItem save(IncomeItem item);

}
