/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income;

import com.pamarin.income.util.PropertiesFileUtils;
import java.io.IOException;
import java.io.Serializable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author jittagornp
 */
@Component
@Scope("application")
public class App implements Serializable{

    private String name;

    public String getName() {
        if (name == null) {
            try {
                name = PropertiesFileUtils
                        .load("/config.properties")
                        .getProperty("app.name");
            } catch (IOException ex) {
                name = null;
            }
        }

        return name;
    }
}
