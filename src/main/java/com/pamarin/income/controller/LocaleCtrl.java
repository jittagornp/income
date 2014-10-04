/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.controller;

import java.io.Serializable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author anonymous
 */
@Component
@Scope("session")
public class LocaleCtrl implements Serializable {

    public String getCalendarFormat() {
        return "dd/MM/yyyy";
    }

    public String getCalendarTimeFormat() {
        return "dd/MM/yyyy HH:mm:ss น.";
    }
}
