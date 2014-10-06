/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.controller;

import com.pamarin.income.model.Settings;
import com.pamarin.income.security.SecurityUtils;
import java.io.Serializable;
import java.util.Currency;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author jittagornp
 */
@Component
@Scope("session")
public class LocaleCtrl implements Serializable {

    private Currency currency;

    public void reset() {
        if (!SecurityUtils.isAnonymous()) {
            Settings settings = SecurityUtils.getUser().getSettings();
            currency = Currency.getInstance(settings.getCurrencyCode());
        }
    }

    public String getCalendarFormat() {
        return "dd/MM/yyyy";
    }

    public String getCalendarTimeFormat() {
        return "dd/MM/yyyy HH:mm:ss น.";
    }

    public Currency getCurrency() {
        return currency;
    }
}
