package com.store.management.tool.service;

import com.store.management.tool.dto.request.ProductCommentDtoRequest;
import com.store.management.tool.dto.response.ProductCommentDtoResponse;
import com.store.management.tool.model.ProductComment;

import java.util.List;

public interface ProductCommentService {

    ProductCommentDtoResponse add(ProductCommentDtoRequest productCommentDtoRequest, Integer productId, Integer customerId);

    ProductCommentDtoResponse getById(Integer id);

    void update(ProductCommentDtoRequest productCommentDtoRequest, Integer id);

    void deleteById(Integer id);
}
