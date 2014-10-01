/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.security;

import com.pamarin.income.model.Account;
import com.pamarin.income.model.User;
import com.pamarin.income.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author jittagornp
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account user = service.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("not found user " + username);
        }

        return user;
    }

}
