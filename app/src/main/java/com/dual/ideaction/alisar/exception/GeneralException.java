package com.dual.ideaction.alisar.exception;

public class GeneralException extends Exception {

    public final static String MESSAGE = "Unknown error";

    public GeneralException(String message) {
        super(message);
    }

    public GeneralException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public GeneralException(Throwable cause) {
        super(cause);
    }

}