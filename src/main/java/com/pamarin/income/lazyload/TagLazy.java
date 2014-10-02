/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.lazyload;

import com.pamarin.income.model.Tag;
import com.pamarin.income.service.TagService;
import com.pamarin.income.util.SpringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author anonymous
 */
public class TagLazy extends LazyLoad<Tag> {

    private final TagService service;

    public TagLazy() {
        service = SpringUtils.getBean(TagService.class);
    }

    @Override
    public Page<Tag> load(Pageable page) {
        return service.findAll(page);
    }

}
