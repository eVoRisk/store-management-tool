package com.store.management.tool.observer.logging;

import lombok.Getter;

@Getter
public enum StructuredArgumentKey {
    ERROR_CODE("error.code");

    private final String value;

    StructuredArgumentKey(final String value) {
        this.value = value;
    }
}
