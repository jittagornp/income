/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pamarin.income.service;

import com.pamarin.income.model.Tag;
import com.pamarin.income.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 *
 * @author jittagornp
 */
public interface TagService {

    Tag save(Tag tag);

    Page<Tag> findByOwner(User user, PageRequest request);
}
