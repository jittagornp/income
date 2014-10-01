/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.util;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author anonymous
 */
public class FacesUtils {

    public static HttpSession getSession() {
        return (HttpSession) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getSession(false);
    }

    public static ServletContext getServletContext() {
        return getSession().getServletContext();
    }
}
