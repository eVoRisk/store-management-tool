package com.store.management.tool.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="fakestore")
public record FakeStoreConfigProperties(String baseUrl, Integer timeout) {

    public static final String PRODUCTS_ENDPOINT = "/products";
    public static final String CATEGORIES_ENDPOINT = "/products/categories";
    public static final String PRODUCTS_BY_CATEGORY_ENDPOINT = "/products/category/{categoryName}";
}
