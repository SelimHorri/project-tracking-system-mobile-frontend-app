package com.selimhorri.pack.exception;

public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException() {

    }

    public ObjectNotFoundException(String message) {
        super(message);
    }

    public ObjectNotFoundException(Throwable cause) {
        super(cause);
    }

    public ObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }



}
