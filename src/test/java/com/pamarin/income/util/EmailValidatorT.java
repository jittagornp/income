/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pamarin.income.util;

import static junit.framework.Assert.assertTrue;
import org.apache.commons.validator.routines.EmailValidator;
import org.testng.annotations.Test;

/**
 *
 * @author anonymous
 */
public class EmailValidatorT {
    
    @Test
    public void test(){
        assertTrue(EmailValidator.getInstance().isValid("jittagornp@gmail.com"));
    }
}
