/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.security;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author jittagornp
 */
public interface BasicAuthen {

    void login(String username, String password) throws UsernameNotFoundException ;
}
