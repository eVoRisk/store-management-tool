package com.store.management.tool.observer.logging;

import com.store.management.tool.model.fakestore.Product;
import com.store.management.tool.observer.ProcessingObserver;
import lombok.RequiredArgsConstructor;
import okhttp3.Request;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ProcessingLoggerProvider {

    private final ProductProcessingLogger productProcessingLogger;
    private final CategoryProcessingLogger categoryProcessingLogger;

    public ProcessingObserver<Request, List<Product>> productLogger() {
        return this.productProcessingLogger;
    }

    public ProcessingObserver<Request, List<String>> categoryLogger() {
        return this.categoryProcessingLogger;
    }
}
