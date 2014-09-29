/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pamarin.income.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.OptimisticLockingFailureException;

/**
 *
 * @author jittagornp
 */
public class Notification {

    private static final Logger LOG = LoggerFactory.getLogger(Notification.class);

    public static void notifyClient(FacesMessage.Severity messageType, String title, String body) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(messageType, title, body));
    }

    public static void notifyPhase(NotifyCallback callback) {
        try {
            callback.beforeProcess();
            callback.process();
            notifyClient(FacesMessage.SEVERITY_INFO, callback.getTitle(), callback.getSuccessBody());
        } catch (OptimisticLockingFailureException ex) {
            LOG.warn(null, ex);
            ex = new OptimisticLockingFailureException("มีผู้ใช้ท่านอื่นได้เปลี่ยนแปลงข้อมูลชุดนี้ไปแล้ว  กรณาทำการโหลดข้อมูลและลองบันทึกใหม่ดูอีกครั้ง");
            notifyClient(FacesMessage.SEVERITY_ERROR, callback.getTitle(), callback.getErrorBody(ex));
        /*} catch (UserException ex) {
            notifyClient(FacesMessage.SEVERITY_ERROR, callback.getTitle(), callback.getErrorBody(ex));
        */} catch (Throwable ex) {
            LOG.warn(null, ex);
            notifyClient(FacesMessage.SEVERITY_ERROR, callback.getTitle(), callback.getErrorBody(ex));
        } finally {
            callback.onFinally();
        }
    }
}
