/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.exception;

/**
 *
 * @author jittagornp
 */
public class UncheckedMailException extends RuntimeException {

    public UncheckedMailException() {
    }

    public UncheckedMailException(String message) {
        super(message);
    }

    public UncheckedMailException(Throwable cause) {
        super(cause);
    }
}
