/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.util;

import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author jittagornp
 */
public class SpringUtils {

    public static <T> T getBean(Class<T> clazz) {
        return WebApplicationContextUtils
                .getWebApplicationContext(FacesUtils.getServletContext())
                .getBean(clazz);
    }

    public static Object getBean(String bean) {
        return WebApplicationContextUtils
                .getWebApplicationContext(FacesUtils.getServletContext())
                .getBean(bean);
    }
}
