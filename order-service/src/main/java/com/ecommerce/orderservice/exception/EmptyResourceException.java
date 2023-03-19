package com.ecommerce.orderservice.exception;

public class EmptyResourceException extends RuntimeException{
    public EmptyResourceException() {
    }

    public EmptyResourceException(String message) {
        super(message);
    }

    public EmptyResourceException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyResourceException(Throwable cause) {
        super(cause);
    }

    public EmptyResourceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
