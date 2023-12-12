package com.store.management.tool.exception;

import lombok.Getter;

import static com.store.management.tool.exception.ErrorCode.*;

@Getter
public class StoreManagementToolException extends RuntimeException {

    private final ErrorCode errorCode;

    public StoreManagementToolException(final ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public StoreManagementToolException(final String message) {
        super(message);
        this.errorCode = INTERNAL_ERROR;
    }

    public StoreManagementToolException(final Throwable cause) {
        super(cause.getMessage());
        this.errorCode = INTERNAL_ERROR;
    }

    public StoreManagementToolException(final ErrorCode errorCode, final Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.errorCode = errorCode;
    }
}
