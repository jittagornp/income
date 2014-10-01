///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.pamarin.income.service.impl;
//
//import com.pamarin.income.model.IncomeItem;
//import com.pamarin.income.repository.IncomeItemRepo;
//import com.pamarin.income.service.IncomeItemService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// *
// * @author jittagornp
// */
//@Service
//@Transactional
//public class IncomeItemServiceImpl implements IncomeItemService {
//
//    @Autowired
//    private IncomeItemRepo repo;
//
//    @Override
//    public Page<IncomeItem> findAll(Pageable page) {
//        return repo.findAll(page);
//    }
//
//    @Override
//    public IncomeItem save(IncomeItem item) {
//        return repo.save(item);
//    }
//
//}
