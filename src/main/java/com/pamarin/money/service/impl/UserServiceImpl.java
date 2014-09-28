/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pamarin.money.service.impl;

import com.pamarin.money.model.User;
import com.pamarin.money.repository.UserRepo;
import com.pamarin.money.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jittagornp
 */
@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo repo;
    
    @Override
    public User save(User user) {
        return repo.save(user);
    }

    @Override
    public boolean hasUser(String username) {
        return repo.countByUsername(username) > 0;
    }

    @Override
    public User findByUsername(String username) {
        return repo.findByUsername(username);
    }
    
}
