package com.store.management.tool.service;

import com.store.management.tool.dto.fakestore.ProductDto;

import java.util.List;

public interface FakeStoreService {

    List<ProductDto> products();

    List<String> categories();

    List<ProductDto> productsByCategory(final String categoryName);
}
