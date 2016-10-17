package com.joseoliveros.messenger.exceptions;

public class DataNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 748191103937898353L;

    public DataNotFoundException(String message) {
        super(message);
    }
}
