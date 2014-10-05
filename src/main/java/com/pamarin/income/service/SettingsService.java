/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.service;

import com.pamarin.income.model.Settings;
import com.pamarin.income.model.User;

/**
 *
 * @author anonymous
 */
public interface SettingsService {

    Settings save(Settings settings);
    
    Settings findByOwner(User owner);
}
