/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.spring;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author anonymous
 */
public class PasswordEncryptor {

    public static String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(11);
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    public static boolean matches(String password, String encryted) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(11);
        return passwordEncoder.matches(password, encryted);
    }
}
