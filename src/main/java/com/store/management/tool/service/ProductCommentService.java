package com.store.management.tool.service;

import com.store.management.tool.dto.ProductCommentDto;
import com.store.management.tool.model.ProductComment;

import java.util.List;

public interface ProductCommentService {

    ProductCommentDto add(ProductCommentDto productCommentDto);

    ProductComment getById(Integer id);

    List<ProductComment> getAll();

    void update(ProductCommentDto productCommentDto, Integer id);

    void deleteById(Integer id);
}
