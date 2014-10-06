/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pamarin.income.repository;

import com.pamarin.income.model.Tag;
import com.pamarin.income.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jittagornp
 */
public interface TagRepo extends JpaRepository<Tag, Integer>{

    public Page<Tag> findByOwner(User user, Pageable pageable);
    
}
