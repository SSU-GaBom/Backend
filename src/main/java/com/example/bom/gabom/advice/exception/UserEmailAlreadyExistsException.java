package com.example.bom.gabom.advice.exception;

public class UserEmailAlreadyExistsException extends RuntimeException{

    public UserEmailAlreadyExistsException(String msg, Throwable t) {
        super(msg, t);
    }

    public UserEmailAlreadyExistsException(String msg) {
        super(msg);
    }

    public UserEmailAlreadyExistsException() {
        super();
    }
}
