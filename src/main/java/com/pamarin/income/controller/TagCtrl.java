/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.controller;

import com.pamarin.income.lazyload.TagLazy;
import com.pamarin.income.model.Tag;
import com.pamarin.income.model.TagListener;
import com.pamarin.income.security.SecurityUtils;
import com.pamarin.income.service.TagService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
public class TagCtrl {

    private static final Logger LOG = LoggerFactory.getLogger(TagCtrl.class);

    private TagLazy tagLazy;
    private Tag tag;
    @Autowired
    private TagService service;
    private List<TagListener> listeners;
    private List<Tag> selected;

    private void reset() {
        tag = null;
        tagLazy = null;
    }

    public TagLazy getTagLazy() {
        if (tagLazy == null) {
            tagLazy = new TagLazy(selected);
        }

        return tagLazy;
    }

    public void setSelected(List<Tag> selected) {
        this.selected = selected;
        reset();
    }

    public Tag getTag() {
        if (tag == null) {
            tag = new Tag();
        }

        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public void onAddTag() {
        try {
            getTag().setOwner(SecurityUtils.getUser());
            getTag().setCreateDate(new Date());
            service.save(getTag());
        } finally {
            reset();
        }
    }

    public void onSelectTags() {
        for (TagListener listener : getTagListeners()) {
            listener.onSelect(getTagLazy().getSelected());
        }
    }

    private List<TagListener> getTagListeners() {
        if (listeners == null) {
            listeners = new ArrayList<>();
        }

        return listeners;
    }

    public void addTagListener(TagListener listener) {
        getTagListeners().add(listener);
    }

    public void onClearSelected() {
        tagLazy.clearSelected();
    }
}
