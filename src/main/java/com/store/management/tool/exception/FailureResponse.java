package com.store.management.tool.exception;

public record FailureResponse(ErrorCode errorCode, String message) {
}
