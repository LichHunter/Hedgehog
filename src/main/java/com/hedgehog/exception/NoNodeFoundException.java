package com.hedgehog.exception;

public class NoNodeFoundException extends RuntimeException {
    public NoNodeFoundException() {
    }

    public NoNodeFoundException(String message) {
        super(message);
    }

    public NoNodeFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoNodeFoundException(Throwable cause) {
        super(cause);
    }

    public NoNodeFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
