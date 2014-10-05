/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.spring;

import junit.framework.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

/**
 *
 * @author anonymous
 */
public class PasswordEncryptorT {

    private static final Logger LOG = LoggerFactory.getLogger(PasswordEncryptorT.class);

    @Test
    public void test() {
        String password = "admin";
        String encrypted = PasswordEncryptor.encrypt(password);
        LOG.debug("password for {} --> {}", password, encrypted);
        Assert.assertTrue(PasswordEncryptor.matches(password, encrypted));
    }
}
