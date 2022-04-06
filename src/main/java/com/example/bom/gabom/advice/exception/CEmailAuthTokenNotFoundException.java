package com.example.bom.gabom.advice.exception;

public class CEmailAuthTokenNotFoundException extends RuntimeException{
    public CEmailAuthTokenNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }

    public CEmailAuthTokenNotFoundException(String msg) {
        super(msg);
    }

    public CEmailAuthTokenNotFoundException() {
        super();
    }
}
