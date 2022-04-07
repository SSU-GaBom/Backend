package com.example.bom.gabom.advice.exception;

public class CUserIdAlreadyExistsException extends RuntimeException{

    public CUserIdAlreadyExistsException(String msg, Throwable t) {
        super(msg, t);
    }

    public CUserIdAlreadyExistsException(String msg) {
        super(msg);
    }
    
    public CUserIdAlreadyExistsException(){
        super();
    }

}
