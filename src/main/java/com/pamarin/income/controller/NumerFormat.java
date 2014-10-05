/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.controller;

import java.io.Serializable;
import java.text.DecimalFormat;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author anonymous
 */
@Component
@Scope("session")
public class NumerFormat implements Serializable {

    public String format(double number) {
        long longNumb = (long) number;
        if (number - longNumb == 0) {
            return (new DecimalFormat("#,###,###,###,##0")).format(number);
        }

        return (new DecimalFormat("#,###,###,###,##0.00")).format(number);
    }
}
