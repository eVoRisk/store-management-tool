package com.store.management.tool.service.client;

import com.store.management.tool.config.FakeStoreConfigProperties;
import com.store.management.tool.dto.fakestore.ProductDto;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface FakeStoreApi {

    @GET(FakeStoreConfigProperties.PRODUCTS_ENDPOINT)
    Response<List<ProductDto>> products();

    @GET(FakeStoreConfigProperties.CATEGORIES_ENDPOINT)
    Response<List<String>> categories();

    @GET(FakeStoreConfigProperties.PRODUCTS_BY_CATEGORY_ENDPOINT)
    Response<List<ProductDto>> productsByCategory(@Path("categoryName") String categoryName);
}
