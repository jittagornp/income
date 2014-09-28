/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.money.util;

import javax.faces.context.FacesContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author jittagornp
 */
public class SpringUtils {

    public static <T> T getBean(Class<T> clazz) {
        return WebApplicationContextUtils
                .getWebApplicationContext(FacesContextUtils
                        .getRequiredWebApplicationContext(FacesContext.getCurrentInstance())
                        .getServletContext())
                .getBean(clazz);
    }

    public static Object getBean(String bean) {
        return WebApplicationContextUtils
                .getWebApplicationContext(FacesContextUtils
                        .getRequiredWebApplicationContext(FacesContext.getCurrentInstance())
                        .getServletContext())
                .getBean(bean);
    }
}
