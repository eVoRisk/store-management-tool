package com.store.management.tool.service;

import com.store.management.tool.dto.CategoryDto;
import com.store.management.tool.model.Category;

import java.util.List;

public interface CategoryService {
    CategoryDto add(CategoryDto categoryDto);

    Category getById(Integer id);

    List<Category> getAll();

    void update(CategoryDto categoryDto, Integer id);

    void deleteById(Integer id);
}
