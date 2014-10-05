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
public class UncheckedIOException extends RuntimeException {

    public UncheckedIOException(String message) {
        super(message);
    }

    public UncheckedIOException(Throwable cause) {
        super(cause);
    }
}
