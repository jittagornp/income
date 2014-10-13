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
 * @author jittagornp
 */
@Component
@Scope("view")
public class ExpensesItemTabCtrl implements Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(ExpensesItemTabCtrl.class);

    private static final String DEFAULT_TAB = "all";
    private static final String TAB_PARAMETER = "tab";
    private String currentTab = DEFAULT_TAB;
    private List<String> allTabs;

    @PostConstruct
    public void postConstruct() {
        changeTab();
    }

    private boolean isTab(File folder) {
        return folder.isDirectory() && !folder.getName().startsWith("_");
    }

    private List<String> readAllTabs() {
        if (allTabs == null) {
            allTabs = new ArrayList<>();
            String realPath = FacesUtils
                    .getServletContext()
                    .getRealPath("/income/list/");
            File folder = new File(realPath);
            for (File tab : folder.listFiles()) {
                if (isTab(tab)) {
                    allTabs.add(tab.getName());
                }
            }
        }

        return allTabs;
    }

    private void changeTab() {
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
        return isTab(tab) ? "active" : "";
    }

    public boolean isTab(String tab) {
        return getCurrentTab().endsWith(tab);
    }
}
