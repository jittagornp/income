/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pamarin.income.service.impl;

import com.pamarin.income.model.Account;
import com.pamarin.income.repository.AccountRepo;
import com.pamarin.income.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jittagornp
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepo repo;
    
    @Override
    public Account save(Account user) {
        return repo.save(user);
    }

    @Override
    public boolean hasAccount(String username) {
        return repo.countByUsername(username) > 0;
    }

    @Override
    public Account findByUsername(String username) {
        return repo.findByUsername(username);
    }
    
}
