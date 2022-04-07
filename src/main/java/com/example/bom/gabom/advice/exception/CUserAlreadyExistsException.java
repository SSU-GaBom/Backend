package com.example.bom.gabom.advice.exception;

public class CUserEmailAlreadyExistsException extends RuntimeException{

    public CUserEmailAlreadyExistsException(String msg, Throwable t) {
        super(msg, t);
    }

    public CUserEmailAlreadyExistsException(String msg) {
        super(msg);
    }

    public CUserEmailAlreadyExistsException() {
        super();
    }
}
