/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.controller;

import com.pamarin.income.component.MailCallback;
import com.pamarin.income.component.MailSender;
import com.pamarin.income.exception.UserException;
import com.pamarin.income.model.User;
import com.pamarin.income.service.UserService;
import com.pamarin.income.spring.PasswordEncryptor;
import com.pamarin.income.util.MessageNotifyCallback;
import com.pamarin.income.util.Notification;
import com.pamarin.income.util.UrlUtils;
import java.io.IOException;
import javax.faces.context.FacesContext;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

/**
 *
 * @author anonymous
 */
@Component
@Scope("view")
public class RegisterUserCtrl {

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
            throw new UserException("email ไม่ถูกต้อง");
        }

        User user = userService.findByUsername(getEmail());
        if (user != null) {
            throw new UserException("email นี้มีถูกใช้งานแล้ว");
        }
    }

    public void checkEmail() {
        try {
            validateCheckEmail();
            errorMessage = "สามารถใช้ email นี้ได้";
        } catch (Exception ex) {
            errorMessage = ex.getMessage();
        }
    }

    public void onCreateUser() {
        Notification.notifyPhase(new MessageNotifyCallback("สร้างบัญชีผู้ใช้ใหม่") {

            @Override
            public void process() throws Throwable {
                validateCheckEmail();
                createUser();
                redirect2CheckEmail();
            }
        });
    }

    private void redirect2CheckEmail() throws IOException {
        FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .redirect(UrlUtils.buildHostUrl() + "/register/checkEmail/?email=" + getEmail());
    }

    private void createUser() {
        final String activateCode = "1234";
        final User user = new User(getEmail(), encryptor.encrypt(getPassword()));
        user.setEnabled(Boolean.FALSE);
        user.setActivateCode(activateCode);
        userService.save(user);

        mailSender.send(new MailCallback() {

            @Override
            public void execute(MimeMessageHelper helper) throws Exception {
                helper.setSubject("ยืนยันบัญชีผู้ใช้โปรแกรมวางแผนรายรับ-รายจ่ายส่วนตัว");
                helper.setTo(user.getUsername());
                helper.setText(
                        "ขอบคุณสำหรับการลงทะเบียนผู้ใช้ \n"
                        + "กรุณายืนยันบัญชีผู้ใช้โดยการคลิก ที่ลิ้งค์นี้ "
                        + UrlUtils.buildHostUrl() + "/register/activate/?code=" + activateCode
                );
            }
        });
    }
}
