package com.chao.dbmanage.common.exception;

public class ServiceException extends RuntimeException {
    public ServiceException() {
        super();
    }
    public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    public ServiceException(String message) {
        super(message);
    }
    public ServiceException(Throwable cause) {
        super(cause);
    }
}
