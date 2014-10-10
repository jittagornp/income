/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.service;

import com.pamarin.income.model.SummaryBeforehand;
import com.pamarin.income.model.pk.SummaryBeforehandPK;

/**
 *
 * @author anonymous
 */
public interface SummaryBeforehandService {

    SummaryBeforehand save(SummaryBeforehand sb);
    
    SummaryBeforehand findById(SummaryBeforehandPK id);
}
