/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pamarin.income.util;

import java.util.Currency;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

/**
 *
 * @author jittagornp
 */
public class CurrencyT {
    
    private static final Logger LOG = LoggerFactory.getLogger(CurrencyT.class);
    
    @Test
    public void test(){
        Set<Currency> currencys = Currency.getAvailableCurrencies();
        for(Currency currency : currencys){
            LOG.debug("currency code --> {}", currency.getCurrencyCode());
            LOG.debug("display name --> {}", currency.getDisplayName());
            LOG.debug("symbol --> {}", currency.getSymbol());
            LOG.debug("numeric code --> {}", currency.getNumericCode());
            LOG.debug("default fraction digits --> {}", currency.getDefaultFractionDigits());
            LOG.debug("------------------------------------------------------");
        }
        
      
    }
}
