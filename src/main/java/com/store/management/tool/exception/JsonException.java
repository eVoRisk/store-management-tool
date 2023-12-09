package com.store.management.tool.exception;

public class JsonException extends StoreManagementToolException {

    public JsonException(final Exception e) {
        super(e.getMessage());
    }
}
