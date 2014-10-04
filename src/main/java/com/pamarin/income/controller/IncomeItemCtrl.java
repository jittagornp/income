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
import com.pamarin.income.security.SecurityUtils;
import com.pamarin.income.service.IncomeItemService;
import com.pamarin.income.util.MessageNotifyCallback;
import com.pamarin.income.util.Notification;
import com.pamarin.income.util.RequestUtils;
import java.util.Date;
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
    //
    private Date startDate;
    private Date endDate;
    //
    @Autowired
    private SummaryIncomeCtrl summaryIncomeCtrl;

    @PostConstruct
    public void postConstruct() {
        tagCtrl.addTagListener(new TagListener() {

            @Override
            public void onSelect(List<Tag> tags) {
                getItem().setTags(tags);
            }
        });

        summaryIncomeCtrl.summary(getStartDate(), getEndDate());
    }

    public IncomeItemLazy getLazy() {
        if (lazy == null) {
            lazy = new IncomeItemLazy();
        }

        return lazy;
    }

    public void onSeach() {
        lazy = new IncomeItemLazy(getStartDate(), getEndDate());
        summaryIncomeCtrl.summary(getStartDate(), getEndDate());
    }

    public void onClear() {
        lazy = new IncomeItemLazy();
        startDate = null;
        endDate = null;
    }

    public void setLazy(IncomeItemLazy lazy) {
        this.lazy = lazy;
    }

    public IncomeItem getItem() {
        if (item == null) {
            onCreateItem();
        }

        return item;
    }

    public void setItem(IncomeItem item) {
        this.item = item;
    }

    public void onCreateItem() {
        item = new IncomeItem();
        item.setIncomeDate(new Date());
        item.setOwner(SecurityUtils.getUser());
    }

    public void onSelectItem() {
        String itemId = RequestUtils.requestString("itemId");
        item = lazy.getRowData(itemId);
    }

    public void findItemById() {
        Integer itemId = RequestUtils.requestInteger("itemId");
        item = itemService.findByIdAndOwner(itemId, SecurityUtils.getUser());
    }

    public void onAddTag() {
        tagCtrl.setSelected(getItem().getTags());
    }

    public void saveItem(String title) {
        Notification.notifyPhase(new MessageNotifyCallback(title) {

            @Override
            public void process() throws Throwable {
                itemService.save(item);
            }
        });
    }

    public void onAddItem() {
        saveItem("เพิ่มรายการค่าใช้จ่าย");
    }

    public void onEditItem() {
        saveItem("แก้ไขรายการค่าใช้จ่าย");
    }

    public void onDeleteItem() {
        Notification.notifyPhase(new MessageNotifyCallback("ลบรายการค่าใช้จ่าย") {

            @Override
            public void process() throws Throwable {
                itemService.delete(item);
            }
        });
    }

    public void onDeleteTag() {
        Integer tagId = RequestUtils.requestInteger("tagId");
        int indexOf = getItem().getTags().indexOf(new Tag(tagId));
        if (indexOf != -1) {
            getItem().getTags().remove(indexOf);
        }
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

}
