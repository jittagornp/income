/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pamarin.income.service;

import com.pamarin.income.model.Account;

/**
 *
 * @author jittagornp
 */
public interface AccountService {
    
    public Account findByUsername(String username);
    
    public Account save(Account user);
    
    public boolean hasAccount(String username);
}
