package com.store.management.tool.exception.model;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder(setterPrefix = "with")
@Jacksonized
public class FakeStoreFailure {

    private String errorCode;
    private String reason;
}
