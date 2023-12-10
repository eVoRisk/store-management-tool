package com.store.management.tool.observer.logging;

import okhttp3.Request;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryProcessingLogger implements LoggerObserver<Request, List<String>> {

    @Override
    public String getSuccessMessage() {
        return "Successful categories request";
    }

    @Override
    public String getFailureMessage() {
        return "Failed categories request with error: [{}]";
    }

    @Override
    public Object[] getStructuredArgumentsRequest(final Request request) {
        return new Object[0];
    }

    @Override
    public Object[] getStructuredArgumentsResponse(final List<String> response) {
        return new Object[0];
    }
}
