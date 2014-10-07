/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.controller;

import com.pamarin.income.model.User;
import com.pamarin.income.security.SecurityUtils;
import java.io.Serializable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author anonymous
 */
@Component
@Scope("application")
public class SessionCtrl implements Serializable {

    private User user;

    public void reset() {
        user = SecurityUtils.getUser();
    }

    public User getUser() {
        return user;
    }

}
