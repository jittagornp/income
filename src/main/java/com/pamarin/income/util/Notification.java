/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.util;

import com.pamarin.income.exception.UserException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.RollbackException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.OptimisticLockingFailureException;

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

    private static void notifyFail(NotifyCallback callback, Throwable ex) {
        notifyClient(
                FacesMessage.SEVERITY_ERROR,
                callback.getTitle(),
                callback.getErrorBody(ex)
        );
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
        } catch (OptimisticLockingFailureException ex) {
            LOG.warn(null, ex);
            notifyFail(callback, new OptimisticLockingFailureException(
                    "มีผู้ใช้ท่านอื่นได้เปลี่ยนแปลงข้อมูลชุดนี้ไปแล้ว กรณาทำการโหลดข้อมูลและลองบันทึกใหม่ดูอีกครั้ง"
            ));
        } catch (UserException ex) {
            notifyFail(callback, ex);
        } catch (RollbackException ex) {
            LOG.warn(null, ex);
            notifyFail(callback, new RollbackException("ไม่สามารถบันทึกข้อมูลได้"));
        } catch (Throwable ex) {
            LOG.warn(null, ex);
            notifyFail(callback, new Throwable("มีข้อผิดพลาดเกิดขึ้นในระหว่างการประมวลผล"));
        } finally {
            callback.onFinally();
        }
    }
}
