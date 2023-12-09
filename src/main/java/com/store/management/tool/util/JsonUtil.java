package com.store.management.tool.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.management.tool.exception.JsonException;
import org.springframework.stereotype.Component;

@Component
public class JsonUtil {

    private final ObjectMapper objectMapper;

    public JsonUtil(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String toJson(final Object value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (final Exception e) {
            throw new JsonException(e);
        }
    }

    public <T> T fromJson(final String json, final Class<T> type) {
        try {
            return objectMapper.readValue(json, type);
        } catch (final Exception e) {
            throw new JsonException(e);
        }
    }
}
