package com.loopme.opinta.exception;

public class InsufficientPermissionException extends RuntimeException {
    public InsufficientPermissionException(String message) {
        super(message);
    }
}
