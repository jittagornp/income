/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *
 * @author jittagornp
 */
@Component
public class PasswordEncryptor {

    @Autowired
    private BCryptPasswordEncoder encoder;

    public String encrypt(String password) {
        return encoder.encode(password);
    }

    public boolean matches(String password, String encryted) {
        return encoder.matches(password, encryted);
    }
}
