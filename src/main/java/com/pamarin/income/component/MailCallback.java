/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.component;

import org.springframework.mail.javamail.MimeMessageHelper;

/**
 *
 * @author anonymous
 */
public interface MailCallback {

    void execute(MimeMessageHelper helper) throws Exception;
}
