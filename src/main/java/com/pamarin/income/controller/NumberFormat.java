/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.controller;

import com.pamarin.income.model.Settings;
import com.pamarin.income.security.SecurityUtils;
import java.io.Serializable;
import java.text.DecimalFormat;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author jittagornp
 */
@Component
@Scope("session")
public class NumberFormat implements Serializable {

    private static final String BEFORE_POINT = "#,###,###,###,##0";

    public String format(double number) {
        if (SecurityUtils.isAnonymous()) {
            return (new DecimalFormat(BEFORE_POINT + ".00")).format(number);

        }
        
        Settings settings = SecurityUtils.getUser().getSettings();
        if (!settings.getForceFloating()) {
            long longNumb = (long) number;
            if (number - longNumb == 0) {
                return (new DecimalFormat(BEFORE_POINT)).format(number);
            }
        }

        String fpoint = StringUtils.rightPad("", settings.getFloatingPoint(), "0");
        if (settings.getFloatingPoint() > 0) {
            fpoint = "." + fpoint;
        }

        return (new DecimalFormat(BEFORE_POINT + fpoint)).format(number);
    }
}
