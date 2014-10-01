/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.controller;

import com.pamarin.income.util.RequestUtils;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author anonymous
 */
@Component
@Scope("view")
public class IncomeItemTabCtrl implements Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(IncomeItemTabCtrl.class);

    private static final String DEFAULT_TAB = "blank";
    private String currentTab = DEFAULT_TAB;

    @PostConstruct
    public void postConstruct() {
        changeTab();
    }

    public void changeTab() {
        try {
            currentTab = RequestUtils.requestString("tab");
            LOG.debug("current tab --> {}", currentTab);
        } catch (Exception ex) {
            currentTab = DEFAULT_TAB;
        }
    }

    public void onChangeTab() {
        changeTab();
    }

    public String getCurrentTab() {
        return currentTab;
    }
}
