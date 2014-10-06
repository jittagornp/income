/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.service.impl;

import com.pamarin.income.model.Suggestion;
import com.pamarin.income.repository.SuggestionRepo;
import com.pamarin.income.service.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jittagornp
 */
@Service
@Transactional
public class SuggestionServiceImpl implements SuggestionService {

    @Autowired
    private SuggestionRepo repo;

    @Override
    public Suggestion save(Suggestion suggestion) {
        return repo.save(suggestion);
    }

}
