/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.util;

import java.util.Date;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

/**
 *
 * @author anonymous
 */
public class JodaTimeT {

    private static final Logger LOG = LoggerFactory.getLogger(JodaTimeT.class);
    
    @Test
    public void firstDateOfThisWeek() {
        LocalDate now = new LocalDate(new Date().getTime());
        LOG.debug("first date of week --> {}", now.withDayOfWeek(DateTimeConstants.MONDAY));
        LOG.debug("last date of week --> {}", now.withDayOfWeek(DateTimeConstants.SUNDAY)); 
    }
}
