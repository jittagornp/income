/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.exception;

/**
 *
 * @author anonymous
 */
public class InvalidMailException extends UncheckedMailException {

    public InvalidMailException() {
        super();
    }

    public InvalidMailException(String message) {
        super(message);
    }

    public InvalidMailException(Throwable cause) {
        super(cause);
    }
}
