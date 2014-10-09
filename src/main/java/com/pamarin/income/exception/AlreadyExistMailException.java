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
public class AlreadyExistMailException extends UncheckedMailException {

    public AlreadyExistMailException() {
    }

    public AlreadyExistMailException(String message) {
        super(message);
    }

    public AlreadyExistMailException(Throwable cause) {
        super(cause);
    }

}
