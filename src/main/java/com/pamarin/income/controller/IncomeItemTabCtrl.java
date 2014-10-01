/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.controller;

import com.pamarin.income.util.FacesUtils;
import com.pamarin.income.util.RequestUtils;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
    private static final String TAB_PARAMETER = "tab";
    private String currentTab = DEFAULT_TAB;
    private List<String> allTabs;

    private List<String> readAllTabs() {
        if (allTabs == null) {
            allTabs = new ArrayList<>();
            String realPath = FacesUtils
                    .getServletContext()
                    .getRealPath("/income/list/");
            File folder = new File(realPath);
            for (File tab : folder.listFiles()) {
                if (tab.isDirectory()) {
                    allTabs.add(tab.getName());
                }
            }
        }

        return allTabs;
    }

    @PostConstruct
    public void postConstruct() {
        changeTab();
    }

    public void changeTab() {
        try {
            currentTab = RequestUtils.requestString(TAB_PARAMETER);
            LOG.debug("current tab --> {}", currentTab);

            if (!readAllTabs().contains(currentTab)) {
                throw new Exception();
            }
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

    public String activeTab(String tab) {
        return getCurrentTab().endsWith(tab) ? "active" : "";
    }
}
