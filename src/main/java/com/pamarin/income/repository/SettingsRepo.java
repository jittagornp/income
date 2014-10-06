/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.repository;

import com.pamarin.income.model.Settings;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jittagornp
 */
public interface SettingsRepo extends JpaRepository<Settings, Integer> {

}
