package com.bsuir.its.exception;

public class PasswordConfirmationException extends Exception {

    public PasswordConfirmationException() {
        super();
    }

    public PasswordConfirmationException(String message) {
        super(message);
    }

    public PasswordConfirmationException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordConfirmationException(Throwable cause) {
        super(cause);
    }

    protected PasswordConfirmationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
