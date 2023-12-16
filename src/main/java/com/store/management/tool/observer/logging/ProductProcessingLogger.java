package com.store.management.tool.observer.logging;

import com.store.management.tool.dto.fakestore.ProductDto;
import okhttp3.Request;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductProcessingLogger implements LoggerObserver<Request, List<ProductDto>> {

    @Override
    public String getSuccessMessage() {
        return "Successful products by category request";
    }

    @Override
    public String getFailureMessage() {
        return "Failed products by category request with error: [{}]";
    }

    @Override
    public Object[] getStructuredArgumentsRequest(final Request request) {
        return new Object[0];
    }

    @Override
    public Object[] getStructuredArgumentsResponse(final List<ProductDto> response) {
        return new Object[0];
    }
}
