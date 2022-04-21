package com.hedgehog.exception;

public class InvalidNumberOfLinesException extends RuntimeException {
    public InvalidNumberOfLinesException() {
    }

    public InvalidNumberOfLinesException(String message) {
        super(message);
    }

    public InvalidNumberOfLinesException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidNumberOfLinesException(Throwable cause) {
        super(cause);
    }

    public InvalidNumberOfLinesException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
