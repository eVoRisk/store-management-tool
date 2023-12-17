package com.store.management.tool.exception;

public class ProductAlreadyInCartException extends RuntimeException {

    public ProductAlreadyInCartException(String message) {
        super(message);
    }
}
