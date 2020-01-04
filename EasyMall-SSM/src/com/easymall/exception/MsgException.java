package com.easymall.exception;

public class MsgException extends RuntimeException {
    public MsgException(){

    }
    public MsgException(String str) {
        super(str);
    }
}
