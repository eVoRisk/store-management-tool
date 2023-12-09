package com.store.management.tool.exception.mapper;

import com.store.management.tool.exception.StoreManagementToolException;

@FunctionalInterface
public interface ExceptionMapper<T> {

    StoreManagementToolException map(T response);
}
