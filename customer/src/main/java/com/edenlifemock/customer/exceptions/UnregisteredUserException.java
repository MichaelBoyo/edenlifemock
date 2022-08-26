package com.edenlifemock.customer.exceptions;

public class UnregisteredUserException extends RuntimeException {
    public UnregisteredUserException(String message) {
        super(message);
    }
}
