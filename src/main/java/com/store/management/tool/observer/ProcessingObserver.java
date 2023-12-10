package com.store.management.tool.observer;

import com.store.management.tool.exception.StoreManagementToolException;

public interface ProcessingObserver<T, V> {

    void process(final T request, final V response);

    void processException(final T request, final StoreManagementToolException e);
}
