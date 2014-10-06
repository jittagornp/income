/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.controller;

import com.pamarin.income.model.User;
import com.pamarin.income.service.UserService;
import com.pamarin.income.util.RequestUtils;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author anonymous
 */
@Component
@Scope("view")
public class ActivateAccountCtrl {

    private static final Logger LOG = LoggerFactory.getLogger(ActivateAccountCtrl.class);

    @Autowired
    private UserService service;
    private String errorMessage;

    @PostConstruct
    public void postConstruct() {
        String failMessage = "การยืนยันบัญชีผู้ใช้ล้มเหลว";
        try {
            String code = RequestUtils.requestString("code");
            User user = service.activateAccount(code);
            if (user == null) {
                errorMessage = failMessage;
            }
        } catch (Exception ex) {
            LOG.warn(null, ex);
            errorMessage = failMessage;
        }
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
