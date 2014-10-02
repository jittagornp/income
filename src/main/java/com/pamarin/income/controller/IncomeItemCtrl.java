/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.controller;

import com.pamarin.income.lazyload.IncomeItemLazy;
import com.pamarin.income.model.IncomeItem;
import com.pamarin.income.model.Tag;
import com.pamarin.income.model.User;
import com.pamarin.income.security.SecurityUtils;
import com.pamarin.income.service.IncomeItemService;
import com.pamarin.income.util.MessageNotifyCallback;
import com.pamarin.income.util.Notification;
import java.util.List;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author jittagornp
 */
@Component
@Scope("view")
public class IncomeItemCtrl {

    private static final Logger LOG = LoggerFactory.getLogger(IncomeItemCtrl.class);
    @Autowired
    private IncomeItemService itemService;
    private IncomeItemLazy lazy;
    private IncomeItem item;
    private User user;
    @Autowired
    private TagCtrl tagCtrl;

    @PostConstruct
    public void postConstruct() {
        reset();
        user = SecurityUtils.getUser();
    }

    private void reset() {
        lazy = new IncomeItemLazy();
    }

    public IncomeItemLazy getLazy() {
        return lazy;
    }

    public IncomeItem getItem() {
        if(item == null){
           item = new IncomeItem(); 
        }
        
        List<Tag> tags = tagCtrl.getTagLazy().getSelected();
        LOG.debug("tags size --> {}", tags.size());
        
        if (!tags.isEmpty()) {
            item.setTags(tags);
        }

        return item;
    }

    public void setItem(IncomeItem item) {
        this.item = item;
    }

    private void setupItem() {
        item.setOwner(user);
    }

    public void onCreateItem() {
        item = new IncomeItem();
    }

    public void onAddItem() {
        Notification.notifyPhase(new MessageNotifyCallback("เพิ่มรายการค่าใช้จ่าย") {

            @Override
            public void process() throws Throwable {
                itemService.save(item);
            }

            @Override
            public void onFinally() {
                reset();
            }

        });
    }
}
