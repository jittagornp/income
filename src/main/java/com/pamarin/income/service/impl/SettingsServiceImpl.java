/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.service.impl;

import com.pamarin.income.model.Settings;
import com.pamarin.income.model.User;
import com.pamarin.income.repository.SettingsRepo;
import com.pamarin.income.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author anonymous
 */
@Service
@Transactional
public class SettingsServiceImpl implements SettingsService {

    @Autowired
    private SettingsRepo repo;

    @Override
    public Settings save(Settings settings) {
        return repo.save(settings);
    }

    @Override
    public Settings findByOwner(User owner) {
        return repo.findOne(owner.getId());
    }

}
