/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.money.service;

import com.pamarin.money.model.TopicIncome;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author anonymous
 */
public interface TopicIncomeService {

    public Page<TopicIncome> findAll(Pageable page);

    public TopicIncome saveTopic(TopicIncome topic);
}
