/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pamarin.income.lazyload;

import com.pamarin.income.model.SelectionModel;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
 
/**
 *
 * @author redcrow
 */
public abstract class SelectionLazyLoad<T> extends LazyLoad<SelectionModel<T>> {
 
    private static final Logger LOG = LoggerFactory.getLogger(SelectionLazyLoad.class);
 
    private Set<T> selected;
    private boolean backup = false;
 
    public SelectionLazyLoad() {
        this(new HashSet<T>());
    }
 
    public SelectionLazyLoad(List<T> selected) {
        this(selected == null ? new HashSet<T>() : new HashSet<>(selected));
    }
 
    public SelectionLazyLoad(Set<T> selected) {
        if (selected == null) {
            this.selected = new HashSet<>();
        }
 
        this.selected = selected;
    }
 
    public abstract Page<T> loadPage(Pageable page);
 
    @Override
    public Page<SelectionModel<T>> load(Pageable page) {
        backupSelected(); // main *****
 
        Page<SelectionModel<T>> result = SelectionModel.toSelection(this.loadPage(page), page);
 
        retureSelected(result); // main *****
        return result;
    }
 
    private void backupSelected() {
        for (SelectionModel<T> item : this.getContents()) {
            if (item.getSelected()) {
                selected.add(item.getData());
            } else {
                selected.remove(item.getData());
            }
        }
 
        backup = true;
    }
 
    private void retureSelected(Page<SelectionModel<T>> result) {
        for (SelectionModel<T> item : result.getContent()) {
            if (selected.contains(item.getData())) {
                item.setSelected(true);
            }
        }
 
        backup = false;
    }
 
    public List<T> getSelected() {
        if (!backup) {
            backupSelected();
        }
 
        return new ArrayList<>(selected);
    }
}