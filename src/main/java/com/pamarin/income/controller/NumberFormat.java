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
import java.text.Format;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author jittagornp
 */
@Component
@Scope("request")
public class NumberFormat implements Serializable {

    private static final String BEFORE_POINT = "#,###,###,###,##0";
    private static final int DEFAULT_FLOATING_POINT = 2;

    private Format createFormat(Integer floatingPoint) {
        String fpoint = "";
        if (floatingPoint > 0) {
            fpoint = "." + StringUtils.rightPad("", floatingPoint, "0");
        }

        return new DecimalFormat(BEFORE_POINT + fpoint);
    }

    private boolean isFloating(double number) {
        return number - (long) number > 0;
    }

    public String format(double number) {
        int floatingPoint;
        if (SecurityUtils.isAnonymous()) {
            floatingPoint = DEFAULT_FLOATING_POINT;
        } else {
            Settings settings = SecurityUtils.getUser().getSettings();
            if (settings.getForceFloating()) {
                floatingPoint = settings.getFloatingPoint();
            } else {
                if (isFloating(number)) {
                    floatingPoint = settings.getFloatingPoint();
                } else {
                    floatingPoint = 0;
                }
            }
        }

        return createFormat(floatingPoint).format(number);
    }
}
