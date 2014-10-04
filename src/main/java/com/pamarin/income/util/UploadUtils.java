/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.util;

import java.io.File;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 *
 * @author anonymous
 */
public class UploadUtils {
    
    public static File getTempDirectory() throws IOException {
        Properties properties = PropertiesFileUtils.load("/config.properties");
        String temp = properties.getProperty("upload.temp.dir");
        File dir = new File(temp);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        return dir;
    }
}
