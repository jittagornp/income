/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author jittagornp
 */
@Service
public class DefaultBasicAuthenImpl implements BasicAuthen {

    @Autowired
    @Qualifier("authenManager")
    private AuthenticationManager manager;
    @Autowired
    private UserDetailsService detailsService;

    /**
     * @param username
     * @param password
     */
    @Override
    public void login(String username, String password) throws UsernameNotFoundException {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        //check authen from user details service
        UserDetails userDetails = detailsService.loadUserByUsername(username);
        Authentication authentication = manager.authenticate(new UsernamePasswordAuthenticationToken(
                userDetails,
                password
        ));
        //keep authentication to security context 
        securityContext.setAuthentication(authentication);
    }
}
