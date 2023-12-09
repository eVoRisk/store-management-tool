package com.store.management.tool.service.client;

import com.store.management.tool.model.Product;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.util.List;

@Service
public class FakeStoreClientImpl implements FakeStoreClient {

    private final FakeStoreApi fakeStoreApi;

    public FakeStoreClientImpl(final FakeStoreApi fakeStoreApi) {
        this.fakeStoreApi = fakeStoreApi;
    }

    @Override
    public List<Product> products() {
        Response<List<Product>> response = fakeStoreApi.products();

        return response.body();
    }

    @Override
    public List<String> categories() {
        Response<List<String>> response = fakeStoreApi.categories();

        return response.body();
    }

    @Override
    public List<Product> productsByCategory(String categoryName) {
        Response<List<Product>> response = fakeStoreApi.productsByCategory(categoryName);

        return response.body();
    }
}
