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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 *
 * @author anonymous
 */
public class TagLazy extends SelectionLazyLoad<Tag> {

    private final TagService service;

    public TagLazy() {
        service = SpringUtils.getBean(TagService.class);
    }

    @Override
    public Page<Tag> loadPage(Pageable page) {
        PageRequest request = new PageRequest(page.getPageNumber(), page.getPageSize(), Sort.Direction.ASC, "id");
        return service.findAll(request);
    }

}
