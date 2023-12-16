package com.store.management.tool.service;

import com.store.management.tool.dto.request.CategoryDtoRequest;
import com.store.management.tool.dto.response.CategoryDtoResponse;

import java.util.List;

public interface CategoryService {
    CategoryDtoResponse add(CategoryDtoRequest categoryDtoRequest);

    CategoryDtoResponse getById(Integer id);

    List<CategoryDtoResponse> getAll();

    void update(CategoryDtoRequest categoryDtoRequest, Integer id);

    void deleteById(Integer id);
}
