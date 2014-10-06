/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.controller;

import com.pamarin.income.exception.UserException;
import com.pamarin.income.model.User;
import com.pamarin.income.security.SecurityUtils;
import com.pamarin.income.service.UserService;
import com.pamarin.income.spring.PasswordEncryptor;
import com.pamarin.income.util.MessageNotifyCallback;
import com.pamarin.income.util.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author jittagornp
 */
@Component
@Scope("view")
public class AccountSettingsCtrl {

    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
    //
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncryptor encryptor;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        if (newPassword == null) {
            newPassword = "";
        }

        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        if (confirmPassword == null) {
            confirmPassword = "";
        }

        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void onSave() {
        Notification.notifyPhase(new MessageNotifyCallback("การตั้งบัญชีผู้ใช้") {

            @Override
            public void process() throws Throwable {
                User user = SecurityUtils.getUser();
                String password = user.getPassword();
                if (!encryptor.matches(oldPassword, password)) {
                    throw new UserException("รหัสผ่านเก่าไม่ถูกต้อง");
                }

                if (!getNewPassword().equals(getConfirmPassword())) {
                    throw new UserException("รหัสผ่านใหม่ ไม่ตรงกับ ยืนยันรหัสผ่านใหม่");
                }

                userService.updatePassword(user, newPassword);
            }

            @Override
            public void onFinally() {
                oldPassword = null;
                newPassword = null;
                confirmPassword = null;
            }

        });
    }
}
