/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.service;

import com.pamarin.income.model.Settings;
import com.pamarin.income.model.User;
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
public class UserServiceT extends AbstractTestNGSpringContextTests {

    @Autowired
    private UserService userService;
    @Autowired
    private SettingsService settingsService;

    @Test
    public void test() {
        if (!userService.hasUser("admin")) {
            User user = userService.save(new User("admin", "admin"));

            Settings settings = new Settings(user);
            settings.setFloatingPoint(2);
            settings.setCurrencyCode("THB");
            settingsService.save(settings);
        }
    }

}
