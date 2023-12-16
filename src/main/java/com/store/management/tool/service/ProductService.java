package com.store.management.tool.service;

import com.store.management.tool.dto.request.ProductDtoRequest;
import com.store.management.tool.dto.response.ProductCommentDtoResponse;
import com.store.management.tool.dto.response.ProductDtoResponse;

import java.util.List;

public interface ProductService {

    ProductDtoResponse add(ProductDtoRequest productDtoRequest);

    ProductDtoResponse getById(Integer id);

    List<ProductDtoResponse> getAll();

    List<ProductCommentDtoResponse> getAllComments(Integer id);

    void update(ProductDtoRequest productDtoRequest, Integer id);

    void deleteById(Integer id);
}
