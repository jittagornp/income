/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.controller;

import com.google.common.io.ByteStreams;
import com.pamarin.income.component.MailCallback;
import com.pamarin.income.component.MailSender;
import com.pamarin.income.exception.UserException;
import com.pamarin.income.model.Suggestion;
import com.pamarin.income.security.SecurityUtils;
import com.pamarin.income.service.SuggestionService;
import com.pamarin.income.util.MessageNotifyCallback;
import com.pamarin.income.util.Notification;
import static com.pamarin.income.util.UploadUtils.getTempDirectory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

/**
 *
 * @author anonymous
 */
@Component
@Scope("view")
public class SuggestionCtrl {

    private static final Logger LOG = LoggerFactory.getLogger(SuggestionCtrl.class);

    private static final String DESTINATION_RECEIVE_EMAIL = "jittagornp@gmail.com";
    private static final String DEFAULT_TYPE = "SUGGESTION";
    private Suggestion suggestion;
    @Autowired
    private SuggestionService service;
    private UploadedFile file;
    @Autowired
    private MailSender mailSender;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public Suggestion getSuggestion() {
        if (suggestion == null) {
            suggestion = new Suggestion();
            suggestion.setType(DEFAULT_TYPE);
        }

        return suggestion;
    }

    public void setSuggestion(Suggestion suggestion) {
        this.suggestion = suggestion;
    }

    private void validateImageType() {
        if (!file.getContentType().contains("image/")) {
            throw new UserException("ต้องเป็นไฟล์รูปภาพเท่านั้น");
        }
    }

    public File getTempDirectoryDateTime() throws IOException {
        Format format = new SimpleDateFormat("dd-MM-yyyy");
        File dateFile = new File(getTempDirectory(), format.format(new Date()));
        if (!dateFile.exists()) {
            dateFile.mkdirs();
        }

        return dateFile;
    }

    private void saveFile() {
        String errorMessage = "ไม่สามารถบันทึกไฟล์รูปภาพได้";
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            String randomName = UUID.randomUUID().toString();
            String extension = FilenameUtils.getExtension(file.getFileName());
            inputStream = file.getInputstream();
            File parentDir = getTempDirectoryDateTime();
            File attachFile = new File(
                    parentDir,
                    randomName + "." + extension
            );

            outputStream = new FileOutputStream(attachFile);
            ByteStreams.copy(inputStream, outputStream);
            getSuggestion().setImage(
                    "/" + parentDir.getName() + "/" + randomName + "." + extension
            );

            sendEmail(attachFile);
        } catch (Exception ex) {
            throw new UserException(errorMessage);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException ex) {
                    throw new UserException(errorMessage);
                }
            }

            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ex) {
                    throw new UserException(errorMessage);
                }
            }
        }
    }

    private void sendEmail(final File attachFile) {
        mailSender.send(new MailCallback() {

            @Override
            public void execute(MimeMessageHelper helper) throws Exception {
                if (attachFile != null) {
                    FileSystemResource file = new FileSystemResource(attachFile);
                    helper.addAttachment(attachFile.getName(), file);
                }

                helper.setSubject("ความคิดเห็นจากผู้ใช้");
                helper.setText(getSuggestion().getType() + " : " + getSuggestion().getMessage());
                helper.setTo(DESTINATION_RECEIVE_EMAIL);
            }
        });
    }

    public void onSave() {
        validateImageType();

        if (!SecurityUtils.isAnonymous()) {
            getSuggestion().setOwner(SecurityUtils.getUser());
        }

        Notification.notifyPhase(new MessageNotifyCallback("ส่งความคิดเห็น") {

            @Override
            public void process() throws Throwable {
                saveFile();
                service.save(suggestion);
            }

            @Override
            public String getSuccessBody() {
                return "เสร็จเรียบร้อย - ขอบคุณสำหรับความคิดเห็น";
            }

            @Override
            public void onFinally() {
                suggestion = null;
            }

        });
    }
}
