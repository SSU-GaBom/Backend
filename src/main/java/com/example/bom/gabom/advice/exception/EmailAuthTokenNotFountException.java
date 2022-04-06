package com.example.bom.gabom.advice.exception;

public class EmailAuthTokenNotFountException extends RuntimeException{
    public EmailAuthTokenNotFountException(String msg, Throwable t) {
        super(msg, t);
    }

    public EmailAuthTokenNotFountException(String msg) {
        super(msg);
    }

    public EmailAuthTokenNotFountException() {
        super();
    }
}
