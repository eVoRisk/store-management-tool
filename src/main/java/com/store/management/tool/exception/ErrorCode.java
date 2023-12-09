package com.store.management.tool.exception;

public enum ErrorCode {

    INTERNAL_ERROR("Internal error");

    private final String message;

    ErrorCode(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
