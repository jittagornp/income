/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author jittagornp
 */
public class PropertiesFileUtils {

    public static Properties load(String path) throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = null;
        
        try {
            inputStream = PropertiesFileUtils.class.getResourceAsStream(path);
            properties.load(inputStream);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }

        return properties;
    }
}
