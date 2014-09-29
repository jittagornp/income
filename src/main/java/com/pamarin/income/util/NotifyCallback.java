/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pamarin.income.util;

/**
 *
 * @author jittagornp
 */
public abstract class NotifyCallback {

    private final String title;

    public NotifyCallback(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
    
    public void beforeProcess() throws Throwable{
        
    }

    public String getSuccessBody() {
        return "เสร็จเรียบร้อย";
    }

    public String getErrorBody(Throwable ex) {
        return "ล้มเหลว เนื่องจาก " + ex.getMessage();
    }

    public abstract void process() throws Throwable;

    public void onFinally() {
        //
    }
}
