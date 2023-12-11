package com.store.management.tool.service.client;

import com.store.management.tool.model.fakestore.Product;

import java.util.List;

public interface FakeStoreClient {

    List<Product> products();

    List<String> categories();

    List<Product> productsByCategory(final String categoryName);
}
