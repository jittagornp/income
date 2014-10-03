/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.controller;

import com.pamarin.income.lazyload.IncomeItemLazy;
import com.pamarin.income.model.IncomeItem;
import com.pamarin.income.model.Tag;
import com.pamarin.income.model.TagListener;
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
    @Autowired
    private TagCtrl tagCtrl;

    @PostConstruct
    public void postConstruct() {
        tagCtrl.addTagListener(new TagListener() {

            @Override
            public void onSelect(List<Tag> tags) {
                getItem().setTags(tags);
            }
        });
    }

    public IncomeItemLazy getLazy() {
        if (lazy == null) {
            lazy = new IncomeItemLazy();
        }

        return lazy;
    }

    public void setLazy(IncomeItemLazy lazy) {
        this.lazy = lazy;
    }

    public IncomeItem getItem() {
        if (item == null) {
            item = new IncomeItem();
        }

        return item;
    }

    public void setItem(IncomeItem item) {
        this.item = item;
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
        });
    }
}
