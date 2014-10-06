/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.spring;

import junit.framework.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 *
 * @author jittagornp
 */
@ContextConfiguration(locations = {
    "/spring/applicationContext-test.xml",
    "/spring/applicationContext-jsf-test.xml",
    "/spring/applicationContext-security-test.xml",
    "/spring/applicationContext-eclipseLink-test.xml"
})
public class PasswordEncryptorT extends AbstractTestNGSpringContextTests {

    private static final Logger LOG = LoggerFactory.getLogger(PasswordEncryptorT.class);

    @Autowired
    private PasswordEncryptor encryptor;

    @Test
    public void test() {
        String password = "admin";
        String encrypted = encryptor.encrypt(password);
        LOG.debug("password for {} --> {}", password, encrypted);
        Assert.assertTrue(encryptor.matches(password, encrypted));
    }
}
