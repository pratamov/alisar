package com.dual.ideaction.alisar.exception;

public class ConnectionException extends Exception {
    public final static String MESSAGE = "Server connection failed";

    public ConnectionException(String message) {
        super(message);
    }

    public ConnectionException(String message, Throwable throwable) {
        super(message, throwable);
    }

}