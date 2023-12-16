package com.store.management.tool.service;

import com.store.management.tool.dto.request.ProductDtoRequest;
import com.store.management.tool.dto.response.ProductDtoResponse;

import java.util.List;

public interface ProductService {

    ProductDtoResponse add(ProductDtoRequest productDto);

    ProductDtoResponse getById(Integer id);

    List<ProductDtoResponse> getAll();

    void update(ProductDtoRequest productDto, Integer id);

    void deleteById(Integer id);
}
