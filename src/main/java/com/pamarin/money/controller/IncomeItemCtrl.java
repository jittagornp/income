/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.money.controller;

import com.pamarin.money.controller.lazy.IncomeItemCtrlLazy;
import com.pamarin.money.controller.lazy.TopicIncomeLazy;
import com.pamarin.money.lazyload.LazyLoad;
import com.pamarin.money.model.IncomeItem;
import com.pamarin.money.model.TopicIncome;
import com.pamarin.money.model.User;
import com.pamarin.money.model.pk.IncomeItemPK;
import com.pamarin.money.security.SecurityUtils;
import com.pamarin.money.service.IncomeItemService;
import com.pamarin.money.service.TopicIncomeService;
import com.pamarin.money.util.Notification;
import com.pamarin.money.util.NotifyCallback;
import com.pamarin.money.util.RequestUtils;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    @Autowired
    private TopicIncomeService topicService;
    private IncomeItemCtrlLazy lazy;
    private TopicIncomeLazy topicLazy;
    private IncomeItem item;
    private TopicIncome topic;

    @PostConstruct
    public void postConstruct() {
        reset();
    }

    private void reset() {
        lazy = new IncomeItemCtrlLazy();
        topicLazy = new TopicIncomeLazy();
    }

    public IncomeItemCtrlLazy getLazy() {
        return lazy;
    }

    public TopicIncomeLazy getTopicLazy() {
        return topicLazy;
    }

    public TopicIncome getTopic() {
        return topic;
    }

    public void setTopic(TopicIncome topic) {
        this.topic = topic;
    }

    public IncomeItem getItem() {
        return item;
    }

    public void setItem(IncomeItem item) {
        this.item = item;
    }

    private void setupItem() {
        User user = SecurityUtils.getUserLogin();
        item.setId(new IncomeItemPK(getTopic().getId(), user.getId()));
        item.setTopic(getTopic());
        item.setOwner(user);
    }

    public void onCreateItem() {
        item = new IncomeItem();
    }

    public void onAddItem() {
        Notification.notifyPhase(new NotifyCallback("เพิ่มรายการค่าใช้จ่าย") {

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

    public void onSelectTopic() {
        String topicId = RequestUtils.requestString("topicId");
        topic = topicLazy.getRowData(topicId);
        if (topic != null) {
            setupItem();
        }
    }

    public void onCreateTopic() {
        topic = new TopicIncome();
    }

    public void onAddTopic() {
        topic = topicService.saveTopic(topic);
        setupItem();
    }
}
