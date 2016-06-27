package com.dual.ideaction.alisar.exception;

public class LoginException extends Exception {
    public final static String MESSAGE = "";

    public LoginException(String message) {
        super(message);
    }

    public LoginException(String message, Throwable throwable) {
        super(message, throwable);
    }

}