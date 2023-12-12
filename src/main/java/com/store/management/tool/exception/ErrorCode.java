package com.store.management.tool.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    INTERNAL_ERROR("Internal error");

    private final String message;

    ErrorCode(final String message) {
        this.message = message;
    }
}
