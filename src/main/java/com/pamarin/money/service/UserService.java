/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pamarin.money.service;

import com.pamarin.money.model.User;

/**
 *
 * @author anonymous
 */
public interface UserService {
    
    public User findByUsername(String username);
    
    public User save(User user);
    
    public boolean hasUser(String username);
}
