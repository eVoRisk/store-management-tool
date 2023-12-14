package com.store.management.tool.exception.mapper;

import com.store.management.tool.exception.FakeStoreFailure;
import com.store.management.tool.exception.StoreManagementToolException;
import org.springframework.stereotype.Component;

@Component
public class FakeStoreExceptionMapper implements ExceptionMapper<FakeStoreFailure> {

    @Override
    public StoreManagementToolException map(final FakeStoreFailure failure) {
        return new StoreManagementToolException(failure.reason());
    }
}
