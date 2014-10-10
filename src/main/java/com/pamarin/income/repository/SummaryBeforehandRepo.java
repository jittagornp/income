/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pamarin.income.repository;

import com.pamarin.income.model.SummaryBeforehand;
import com.pamarin.income.model.pk.SummaryBeforehandPK;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author anonymous
 */
public interface SummaryBeforehandRepo extends JpaRepository<SummaryBeforehand, SummaryBeforehandPK>{
    
}
