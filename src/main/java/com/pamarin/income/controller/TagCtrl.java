/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.controller;

import com.pamarin.income.lazyload.TagLazy;
import com.pamarin.income.model.Tag;
import com.pamarin.income.security.SecurityUtils;
import com.pamarin.income.service.TagService;
import java.util.Date;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 *
 * @author anonymous
 */
@Component
@Scope("view")
public class TagCtrl {
    
    private static final Logger LOG = LoggerFactory.getLogger(TagCtrl.class);

    private TagLazy tagLazy;
    private Tag tag;
    @Autowired
    private TagService service;

    @PostConstruct
    public void postConstruct() {
        reset();
    }

    private void reset() {
        tagLazy = new TagLazy();
        tag = new Tag();
    }

    public TagLazy getTagLazy() {
        return tagLazy;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public void onAddTag() {
        try {
            tag.setOwner(SecurityUtils.getUser());
            tag.setCreateDate(new Date());
            service.save(tag);
        } finally {
            reset();
        }
    }
    
    public void onSelectTags(){
        LOG.debug("call onSelectTags");
        for(Tag t : getTagLazy().getSelected()){
            LOG.debug("tag --> {}", t.getName());
        }
        
        //tagLazy.clearSelected();
    }
    
    public void onClearSelected(){
        tagLazy.clearSelected();
    }
}
