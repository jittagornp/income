/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.util;

import java.util.Random;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author jittagornp
 */
public class StringRandom {

    public static String random2048bit() {
        byte[] r = new byte[256]; //Means 2048 bit
        Random random = new Random();
        random.nextBytes(r);

        return Base64.encodeBase64String(r)
                .replace("+", "_");
    }
}
