/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.service.impl;

import com.pamarin.income.model.Tag;
import com.pamarin.income.model.User;
import com.pamarin.income.repository.TagRepo;
import com.pamarin.income.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jittagornp
 */
@Service
@Transactional
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepo repo;

    @Override
    public Tag save(Tag tag) {
        return repo.save(tag);
    }

    @Override
    public Page<Tag> findByOwner(User user, PageRequest request) {
        return repo.findByOwner(user, request);
    }

}
