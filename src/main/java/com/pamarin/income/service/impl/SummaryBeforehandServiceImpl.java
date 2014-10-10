/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.service.impl;

import com.pamarin.income.model.SummaryBeforehand;
import com.pamarin.income.model.pk.SummaryBeforehandPK;
import com.pamarin.income.repository.SummaryBeforehandRepo;
import com.pamarin.income.service.SummaryBeforehandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author anonymous
 */
@Service
@Transactional
public class SummaryBeforehandServiceImpl implements SummaryBeforehandService {

    @Autowired
    private SummaryBeforehandRepo repo;

    @Override
    public SummaryBeforehand save(SummaryBeforehand sb) {
        return repo.save(sb);
    }

    @Override
    public SummaryBeforehand findById(SummaryBeforehandPK id) {
        return repo.findOne(id);
    }

}
