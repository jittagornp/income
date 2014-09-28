/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.money.repository;

import com.pamarin.money.model.IncomeItem;
import com.pamarin.money.model.pk.IncomeItemPK;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jittagornp
 */
public interface IncomeItemRepo extends JpaRepository<IncomeItem, IncomeItemPK> {

}
