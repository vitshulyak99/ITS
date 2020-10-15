package com.bsuir.its.exception;

public class UserWithEmailIsExistException extends Exception {
    public UserWithEmailIsExistException() {
        super();
    }

    public UserWithEmailIsExistException(String message) {
        super(message);
    }

    public UserWithEmailIsExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserWithEmailIsExistException(Throwable cause) {
        super(cause);
    }

    protected UserWithEmailIsExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
