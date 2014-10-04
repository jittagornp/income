/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.controller;

import com.pamarin.income.model.Suggestion;
import com.pamarin.income.security.SecurityUtils;
import com.pamarin.income.service.SuggestionService;
import com.pamarin.income.util.MessageNotifyCallback;
import com.pamarin.income.util.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author anonymous
 */
@Component
@Scope("view")
public class SuggestionCtrl {

    private Suggestion suggestion;
    @Autowired
    private SuggestionService service;

    public Suggestion getSuggestion() {
        if (suggestion == null) {
            suggestion = new Suggestion();
            suggestion.setType("SUGGESTION");
        }

        return suggestion;
    }

    public void setSuggestion(Suggestion suggestion) {
        this.suggestion = suggestion;
    }

    public void onSave() {
        if (!SecurityUtils.isAnonymous()) {
            getSuggestion().setOwner(SecurityUtils.getUser());
        }

        Notification.notifyPhase(new MessageNotifyCallback("ส่งความคิดเห็น") {

            @Override
            public void process() throws Throwable {
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
