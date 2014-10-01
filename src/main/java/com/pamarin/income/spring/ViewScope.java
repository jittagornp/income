/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.spring;

import java.util.Map;

import javax.faces.context.FacesContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

public class ViewScope implements Scope {

    private static final Logger LOG = LoggerFactory.getLogger(ViewScope.class);
    
    private Map<String, Object> getViewMap() {
        return FacesContext.getCurrentInstance()
                .getViewRoot()
                .getViewMap();
    }

    @Override
    public Object get(String name, ObjectFactory objectFactory) {
        Object object;
        Map<String, Object> viewMap = getViewMap();

        if (viewMap.containsKey(name)) {
            object = viewMap.get(name);
        } else {
            object = objectFactory.getObject();
            viewMap.put(name, object);
        }
        
        LOG.debug("get scope --> {}, {}", name, object);
        
        return object;
    }

    @Override
    public Object remove(String name) {
        LOG.debug("remove scope --> {}", name);
        return getViewMap().remove(name);
    }

    @Override
    public String getConversationId() {
        return null;
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
        //Not supported
    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }
}
