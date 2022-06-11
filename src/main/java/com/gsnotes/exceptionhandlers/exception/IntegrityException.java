package com.gsnotes.exceptionhandlers.exception;

public class IntegrityException extends Exception {

    public IntegrityException(String message) {
        super(message);
    }

    public IntegrityException(String message, Throwable cause) {
        super(message, cause);
    }


}
