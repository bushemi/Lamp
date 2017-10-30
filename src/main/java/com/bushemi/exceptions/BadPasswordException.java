package com.bushemi.exceptions;

/**
 * Created by igor on 17.10.17.
 * useless comment
 */
public class BadPasswordException extends  RuntimeException {

    public BadPasswordException(String message) {
        super(message);
    }
}
