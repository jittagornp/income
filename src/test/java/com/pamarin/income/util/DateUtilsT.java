/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.util;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author anonymous
 */
public class DateUtilsT {

    @Test
    public void mergeDateTime() {
        Date date = DateUtils.buildDate(1, 1, 2557);
        Date time = DateUtils.buildTime(15, 30, 0);
        
        Date merged = DateUtils.mergeDateTime(date, time);
        
        Format formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        assertEquals("01/01/2557 15:30:00", formatter.format(merged));
    }
}
