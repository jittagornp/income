/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.controller;

import com.pamarin.income.model.Settings;
import com.pamarin.income.security.SecurityUtils;
import com.pamarin.income.service.SettingsService;
import com.pamarin.income.util.MessageNotifyCallback;
import com.pamarin.income.util.Notification;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Currency;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author anonymous
 */
@Component
@Scope("view")
public class NormalSettingsCtrl {

    private List<Currency> currencys;
    private Settings settings;
    @Autowired
    private SettingsService settingsService;

    @PostConstruct
    public void postConstruct() {
        settings = settingsService.findByOwner(SecurityUtils.getUser());
    }

    public List<Currency> getCurrencys() {
        if (currencys == null) {
            currencys = new ArrayList<>(Currency.getAvailableCurrencies());
            Collections.sort(currencys, new Comparator<Currency>() {

                @Override
                public int compare(Currency o1, Currency o2) {
                    return o1.getSymbol().compareTo(o2.getSymbol());
                }
            });
        }

        return currencys;
    }

    public Settings getSettings() {
        return settings;
    }

    public void onSave() {
        Notification.notifyPhase(new MessageNotifyCallback("การตั้งค่าทั่วไป") {

            @Override
            public void process() throws Throwable {
                settingsService.save(settings);
            }
        });
    }
}
