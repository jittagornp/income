/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.util;

import com.pamarin.income.exception.ErrorMessage;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jittagornp
 */
public class Notification {

    private static final Logger LOG = LoggerFactory.getLogger(Notification.class);

    public static void notifyClient(
            FacesMessage.Severity messageType,
            String title,
            String body
    ) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(messageType, title, body));
    }

    public static void notifyPhase(NotifyCallback callback) {
        try {
            callback.beforeProcess();
            callback.process();
            notifyClient(
                    FacesMessage.SEVERITY_INFO,
                    callback.getTitle(),
                    callback.getSuccessBody()
            );
        } catch (Throwable ex) {
            LOG.warn(null, ex);
            notifyClient(
                    FacesMessage.SEVERITY_ERROR,
                    callback.getTitle(),
                    callback.getErrorBody(new Throwable(ErrorMessage.from(ex)))
            );
        } finally {
            callback.onFinally();
        }
    }
}
