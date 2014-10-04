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
 * @author anonymous
 */
public class PropertiesFileUtils {

    private static Properties properties;

    public static synchronized Properties load(String path) throws IOException {
        if (properties == null) {
            properties = new Properties();
        }

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
