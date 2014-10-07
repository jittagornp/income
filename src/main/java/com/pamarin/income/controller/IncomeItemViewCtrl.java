/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.controller;

import com.pamarin.income.util.RequestUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author anonymous
 */
@Component
@Scope("view")
public class IncomeItemViewCtrl {

    private String view = "item";

    public void onChangeView() {
        this.view = RequestUtils.requestString("view");
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public boolean isView(String view) {
        return this.view.equals(view);
    }

    public String active(String view) {
        return isView(view) ? "active" : "";
    }
}
