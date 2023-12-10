package com.store.management.tool.service;

import com.store.management.tool.model.Product;
import com.store.management.tool.observer.logging.ProcessingLoggerProvider;
import com.store.management.tool.service.client.FakeStoreClient;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.store.management.tool.observer.Observability.executeWithObserver;

@Service
public class FakeStoreServiceImpl implements FakeStoreService {

    private final FakeStoreClient fakeStoreClient;
    private final ProcessingLoggerProvider loggerProvider;

    public FakeStoreServiceImpl(final FakeStoreClient fakeStoreClient,
                                final ProcessingLoggerProvider loggerProvider) {
        this.fakeStoreClient = fakeStoreClient;
        this.loggerProvider = loggerProvider;
    }

    @Override
    public List<Product> products() {
        return executeWithObserver(this::doProducts,
                loggerProvider::productLogger, null);
    }

    @Override
    public List<String> categories() {
        return executeWithObserver(this::doCategories,
                loggerProvider::categoryLogger, null);
    }

    @Override
    public List<Product> productsByCategory(String categoryName) {
        return fakeStoreClient.productsByCategory(categoryName);
    }

    private List<Product> doProducts(final Object o) {
        return fakeStoreClient.products();
    }

    private List<String> doCategories(final Object o) {
        return fakeStoreClient.categories();
    }
}
