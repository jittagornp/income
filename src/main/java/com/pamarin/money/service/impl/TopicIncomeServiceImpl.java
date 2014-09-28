/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pamarin.money.service.impl;

import com.pamarin.money.model.TopicIncome;
import com.pamarin.money.repository.TopicIncomeRepo;
import com.pamarin.money.service.TopicIncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author anonymous
 */
@Service
@Transactional
public class TopicIncomeServiceImpl implements TopicIncomeService{

    @Autowired
    private TopicIncomeRepo repo;
    
    @Override
    public TopicIncome saveTopic(TopicIncome topic) {
        return repo.save(topic);
    }

    @Override
    public Page<TopicIncome> findAll(Pageable page) {
        return repo.findAll(page);
    }
    
}
