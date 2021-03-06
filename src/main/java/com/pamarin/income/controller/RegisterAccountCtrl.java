/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.controller;

import com.pamarin.income.App;
import com.pamarin.income.component.MailCallback;
import com.pamarin.income.component.MailSender;
import com.pamarin.income.exception.AlreadyExistMailException;
import com.pamarin.income.exception.ErrorMessage;
import com.pamarin.income.exception.InvalidMailException;
import com.pamarin.income.model.Settings;
import com.pamarin.income.model.User;
import com.pamarin.income.security.BasicAuthen;
import com.pamarin.income.service.SettingsService;
import com.pamarin.income.service.UserService;
import com.pamarin.income.spring.PasswordEncryptor;
import com.pamarin.income.util.MessageNotifyCallback;
import com.pamarin.income.util.Notification;
import com.pamarin.income.util.StringRandom;
import com.pamarin.income.util.UrlUtils;
import java.io.IOException;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 *
 * @author jittagornp
 */
@Component
@Scope("view")
public class RegisterAccountCtrl {

    private String email;
    private String password;
    private String errorMessage;
    //
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncryptor encryptor;
    @Autowired
    private MailSender mailSender;
    @Autowired
    private SettingsService settingsService;
    @Autowired
    private BasicAuthen authen;
    @Autowired
    private App app;

    public String getEmail() {
        if (email == null) {
            email = "";
        }

        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        if (password == null) {
            password = "";
        }

        return password;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private boolean isEmail() {
        return EmailValidator.getInstance().isValid(getEmail());
    }

    private void validateCheckEmail() {
        if (!isEmail()) {
            throw new InvalidMailException();
        }

        User user = userService.findByUsername(getEmail());
        if (user != null) {
            throw new AlreadyExistMailException();
        }
    }

    public void checkEmail() {
        try {
            validateCheckEmail();
            errorMessage = "สามารถใช้ email นี้ได้";
        } catch (Exception ex) {
            errorMessage = ErrorMessage.from(ex);
        }
    }

    private void immediateAuthen() {
        authen.login(getEmail(), getPassword());
    }

    private void checkAccount() throws Exception {
        try {
            validateCheckEmail();
        } catch (AlreadyExistMailException ex) {
            try {
                immediateAuthen();
                redirect2HomePage();
                return;
            } catch (AuthenticationException aex) {
                /* swallow exception */
            }

            throw ex;
        }
    }

    public void onCreateUser() {
        Notification.notifyPhase(new MessageNotifyCallback("สร้างบัญชีผู้ใช้ใหม่") {

            @Override
            public void process() throws Throwable {
                checkAccount();

                String activateCode = createUser();
                sendEmail(activateCode);
                redirect2CheckEmail();
            }
        });
    }

    private void redirect2HomePage() throws IOException {
        UrlUtils.redirectPath2("/");
    }

    private void redirect2CheckEmail() throws IOException {
        UrlUtils.redirectPath2("/register/checkEmail/?email=" + getEmail());
    }

    private void sendEmail(final String activateCode) {
        mailSender.send(new MailCallback() {

            @Override
            public void execute(MimeMessageHelper helper) throws Exception {
                helper.setSubject("ยืนยันบัญชีผู้ใช้" + app.getName());
                helper.setTo(getEmail());
                helper.setText(
                        "ขอบคุณสำหรับการลงทะเบียนผู้ใช้ "
                        + "กรุณายืนยันบัญชีผู้ใช้โดยการคลิก ที่ลิ้งค์นี้ "
                        + UrlUtils.buildHostUrl() + "/register/activate/?code=" + activateCode
                );
            }
        });
    }

    private String createUser() {
        String activateCode = StringRandom.random2048bit();
        User user = new User(getEmail(), encryptor.encrypt(getPassword()));
        user.setEnabled(Boolean.FALSE);
        user.setActivateCode(activateCode);
        user = userService.save(user);
        settingsService.save(Settings.createDefaults(user));

        return activateCode;
    }
}
