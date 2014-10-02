/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.lazyload;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.Id;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 *
 * @author jittagornp
 */
public abstract class LazyLoad<T> extends LazyDataModel<T> {

    private static final Logger LOG = LoggerFactory.getLogger(LazyLoad.class);
    private List<T> list;
    private long totalElements;
    //

    @Override
    public T getRowData(String id) {
        if (list != null) {
            for (T instance : list) {
                Object instanceId = getIdOfInstance(instance);
                if (instanceId != null && id.equals(instanceId.toString())) {
                    return instance;
                }
            }
        }

        return null;
    }

    private Object getIdOfInstance(Object instance) {
        Object instanceId = null;
        try {
            Class instanceClass = instance.getClass();
            Field[] fields = instanceClass.getDeclaredFields();
            Field idField = null;
            for (Field field : fields) {
                if (field.isAnnotationPresent(Id.class)) {
                    idField = field;
                    break;
                }
            }

            if (idField != null) {
                String idName = idField.getName();
                idName = idName.substring(0, 1).toUpperCase() + idName.substring(1);
                Method method = instanceClass.getDeclaredMethod("get" + idName);
                instanceId = method.invoke(instance);
            }
        } catch (Exception ex) {
            LOG.warn(null, ex);
        }

        return instanceId;
    }

    @Override
    public Object getRowKey(T instance) {
        return getIdOfInstance(instance);
    }

    @Override
    public void setRowIndex(final int rowIndex) {
        if (rowIndex == -1 || getPageSize() == 0) {
            super.setRowIndex(-1);
        } else {
            super.setRowIndex(rowIndex % getPageSize());
        }
    }

    /**
     * callback method
     * @param page
     * @return 
     */
    public abstract Page<T> load(Pageable page);

    @Override
    public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        Sort.Direction direction;
        if (sortOrder == SortOrder.ASCENDING) {
            direction = Sort.Direction.ASC;
        } else {
            direction = Sort.Direction.DESC;
        }
        
        if(sortField == null){
            sortField = "id";
        }

        Page<T> page = load(new PageRequest(first / pageSize, pageSize, direction, sortField));
        if (page != null) {
            list = page.getContent();
            totalElements = page.getTotalElements();
            this.setRowCount((int) totalElements);
        } else {
            list = new ArrayList<>();
            totalElements = 0L;
            setRowCount(0);
        }

        return list;
    }

    public List<T> getContents() {
        if(list == null){
            list = new ArrayList<>();
        }
        
        return list;
    }

    public long getTotalElements() {
        return totalElements;
    }
}
