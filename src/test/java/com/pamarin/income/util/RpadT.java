/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pamarin.income.util;

import junit.framework.Assert;
import org.apache.commons.lang.StringUtils;
import org.testng.annotations.Test;

/**
 *
 * @author anonymous
 */
public class RpadT {
    
    @Test
    public void test(){
        Assert.assertEquals("00", StringUtils.rightPad("0", 2, "0"));
        Assert.assertEquals("000", StringUtils.rightPad("0", 3, "0"));
        Assert.assertEquals("00000", StringUtils.rightPad("0", 5, "0"));
        Assert.assertEquals("000000000", StringUtils.rightPad("0", 9, "0"));
        
        Assert.assertEquals("0000000", StringUtils.rightPad("", 7, "0"));
        Assert.assertEquals("", StringUtils.rightPad("", 0, "0"));
    }
}
