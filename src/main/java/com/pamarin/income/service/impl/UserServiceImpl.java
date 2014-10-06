/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.service.impl;

import com.pamarin.income.model.User;
import com.pamarin.income.repository.UserRepo;
import com.pamarin.income.service.UserService;
import com.pamarin.income.spring.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author anonymous
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo repo;
    @Autowired
    private PasswordEncryptor encryptor;

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

    @Override
    public void updatePassword(User user, String password) {
        String encrypted = encryptor.encrypt(password);
        repo.updatePassword(user.getId(), encrypted);
        user.setPassword(encrypted);
    }

    @Override
    public User activateAccount(String code) {
        User user = repo.findByActivateCode(code);
        if (user == null) {
            return null;
        }

        user.setActivateCode(null);
        user.setEnabled(Boolean.TRUE);
        return user;
    }

}
