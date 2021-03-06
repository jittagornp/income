/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pamarin.income.repository;

import com.pamarin.income.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author jittagornp
 */
public interface UserRepo extends JpaRepository<User, Integer>{

    @Query("SELECT COUNT(u) FROM User u WHERE u.username = ?1")
    public long countByUsername(String username);

    public User findByUsername(String username);

    @Modifying
    @Query("UPDATE User u SET u.password = ?2 WHERE u.id = ?1")
    public void updatePassword(Integer id, String password);
    
    public User findByActivateCode(String code);
}
