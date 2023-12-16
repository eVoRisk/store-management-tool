package com.store.management.tool.service;

import com.store.management.tool.dto.request.ProductCommentDtoRequest;
import com.store.management.tool.dto.response.ProductCommentDtoResponse;
import com.store.management.tool.model.ProductComment;

import java.util.List;

public interface ProductCommentService {

    ProductCommentDtoResponse add(ProductCommentDtoRequest productCommentDtoRequest);

    ProductCommentDtoResponse getById(Integer id);

    List<ProductCommentDtoResponse> getAll();

    void update(ProductCommentDtoRequest productCommentDtoRequest, Integer id);

    void deleteById(Integer id);
}
