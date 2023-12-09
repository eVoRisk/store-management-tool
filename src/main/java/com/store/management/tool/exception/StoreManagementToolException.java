package com.store.management.tool.exception;

import static com.store.management.tool.exception.ErrorCode.*;

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

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
