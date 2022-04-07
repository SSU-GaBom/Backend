package com.example.bom.gabom.advice.exception;

public class CCheckNickNameFailedException extends RuntimeException{

    public CCheckNickNameFailedException(String msg, Throwable t) {
        super(msg, t);
    }

    public CCheckNickNameFailedException(String msg) {
        super(msg);
    }

    public CCheckNickNameFailedException(){
        super();
    }

}
