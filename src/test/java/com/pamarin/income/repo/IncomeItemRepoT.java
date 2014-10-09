/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.repo;

import com.pamarin.income.model.IncomeItem;
import com.pamarin.income.model.Statistic;
import com.pamarin.income.model.User;
import com.pamarin.income.repository.IncomeItemRepo;
import java.util.List;
import junit.framework.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
public class IncomeItemRepoT extends AbstractTestNGSpringContextTests {

    @Autowired
    private IncomeItemRepo repo;

//    @Test
//    public void findMaxItemByOwner() {
//        User owner = new User();
//        owner.setId(1);
//        PageRequest request = new PageRequest(0, 1, Sort.Direction.DESC, "incomeValue");
//        Page<Statistic> result = repo.findItemByOwner(owner, request);
//        Assert.assertEquals("yyy", result.getContent().get(0).getKey());
//    }

    @Test
    public void findByOwner() {
        User owner = new User();
        owner.setId(1);
        List<IncomeItem> items = repo.findByOwner(owner);
        Assert.assertEquals(5, items.size());
        Assert.assertEquals("ทดสอบ", items.get(0).getIncomeName());
        Assert.assertEquals("www", items.get(4).getIncomeName());
    }
}
