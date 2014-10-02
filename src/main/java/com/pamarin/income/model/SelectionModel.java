/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pamarin.income.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
 
/**
 *
 * @author redcrow
 */
public class SelectionModel<T> implements Serializable {
 
    private T data;
    private boolean selected = false;
     
    private SelectionModel(){
        //
    }
 
    public T getData() {
        return data;
    }
 
    public void setData(T data) {
        this.data = data;
    }
 
    public boolean getSelected() {
        return selected;
    }
 
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
 
    public static <T> List<SelectionModel<T>> toSelection(List<T> list) {
        List<SelectionModel<T>> result = new ArrayList<>();
        for (T item : list) {
            result.add(toSelection(item));
        }
 
        return result;
    }
 
    public static <T> SelectionModel<T> toSelection(T item) {
        SelectionModel<T> model = new SelectionModel<>();
        model.setData(item);
        return model;
    }
 
    public static <T> Page<SelectionModel<T>> toSelection(Page<T> page, Pageable pageable) {
        return new PageImpl(toSelection(page.getContent()), pageable, page.getTotalElements());
    }
 
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.data);
        return hash;
    }
 
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SelectionModel<?> other = (SelectionModel<?>) obj;
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        return true;
    }
 
}