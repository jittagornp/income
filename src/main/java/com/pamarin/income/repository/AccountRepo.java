/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pamarin.income.repository;

import com.pamarin.income.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author anonymous
 */
public interface AccountRepo extends JpaRepository<Account, Integer>{

    @Query("SELECT COUNT(a) FROM Account a WHERE a.username = ?1")
    public long countByUsername(String username);

    public Account findByUsername(String username);
}
