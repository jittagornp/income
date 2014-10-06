/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.repo;

import com.pamarin.income.model.Statistic;
import com.pamarin.income.model.User;
import com.pamarin.income.repository.NativeIncomeItemRepo;
import java.util.Date;
import static junit.framework.Assert.*;
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
public class NativeIncomeItemRepoT extends AbstractTestNGSpringContextTests {

    @Autowired
    private NativeIncomeItemRepo repo;

    @Test
    public void test() {
        User owner = new User();
        owner.setId(1);
        Statistic statistic = repo.findItemGroupByOwner(owner, "DESC", new Date(), new Date());
        assertEquals("โอที", statistic.getKey());
    }
}
