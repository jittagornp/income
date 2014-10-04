/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.component;

import com.pamarin.income.exception.UncheckedMailException;
import com.pamarin.income.util.PropertiesFileUtils;
import java.io.IOException;
import java.util.Properties;
import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

/**
 *
 * @author jittagornp
 */
@Component
public class MailSenderImpl implements MailSender {

    private static final String SMTP_HOST_NAME = "smtp.gmail.com";

    static {
        MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
        mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
        mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
        mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
        mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
        mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
        CommandMap.setDefaultCommandMap(mc);
    }

    @Override
    public void send(MailCallback callback) {
        try {
            JavaMailSenderImpl sender = new JavaMailSenderImpl();
            Properties config = loadMailConfig();

            Properties pro = new Properties();
            pro.setProperty("mail.smtps.auth", "true");
            pro.setProperty("mail.smtps.starttls.enable", "true");
            pro.setProperty("mail.debug", "true");

            sender.setJavaMailProperties(pro);
            sender.setUsername(config.getProperty("email.username"));
            sender.setPassword(config.getProperty("email.password"));
            sender.setProtocol("smtps");
            sender.setPort(465);
            sender.setHost(SMTP_HOST_NAME);
            sender.setDefaultEncoding("utf-8");

            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            callback.execute(helper);

            sender.send(message);
        } catch (Exception ex) {
            throw new UncheckedMailException(ex);
        }
    }

    private Properties loadMailConfig() throws IOException {
        return PropertiesFileUtils.load("/email.properties");
    }
}
