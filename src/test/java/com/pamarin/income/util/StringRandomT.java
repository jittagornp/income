/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pamarin.income.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

/**
 *
 * @author jittagornp
 */
public class StringRandomT {
    
    private static final Logger LOG = LoggerFactory.getLogger(StringRandomT.class);
    
    @Test
    public void test(){
        LOG.debug("random --> {}", StringRandom.random2048bit());
    }
}
