package com.store.management.tool.service;

import com.store.management.tool.model.Product;
import com.store.management.tool.service.client.FakeStoreClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FakeStoreServiceImpl implements FakeStoreService {

    private final FakeStoreClient fakeStoreClient;

    public FakeStoreServiceImpl(final FakeStoreClient fakeStoreClient) {
        this.fakeStoreClient = fakeStoreClient;
    }

    @Override
    public List<Product> products() {
        return fakeStoreClient.products();
    }

    @Override
    public List<String> categories() {
        return fakeStoreClient.categories();
    }

    @Override
    public List<Product> productsByCategory(String categoryName) {
        return fakeStoreClient.productsByCategory(categoryName);
    }
}
