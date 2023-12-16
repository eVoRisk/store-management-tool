package com.store.management.tool.service;

import com.store.management.tool.dto.request.CategoryDtoRequest;
import com.store.management.tool.dto.response.CategoryDtoResponse;
import com.store.management.tool.exception.NotFoundException;
import com.store.management.tool.model.Category;
import com.store.management.tool.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final ModelMapper modelMapper;

    @Override
    public CategoryDtoResponse add(CategoryDtoRequest categoryDtoRequest) {
        var category = categoryRepository.save(modelMapper.map(categoryDtoRequest, Category.class));

        return modelMapper.map(category, CategoryDtoResponse.class);
    }

    @Override
    public CategoryDtoResponse getById(Integer id) {
        var category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(format("Category with id: %s not found", id)));

        return modelMapper.map(category, CategoryDtoResponse.class);
    }

    @Override
    public List<CategoryDtoResponse> getAll() {
        return categoryRepository.findAll().stream()
                .map(category -> modelMapper.map(category, CategoryDtoResponse.class)).toList();
    }

    @Override
    public void update(CategoryDtoRequest categoryDtoRequest, Integer id) {
        Optional<Category> category = categoryRepository.findById(id);
        category.ifPresent(value -> modelMapper.map(categoryDtoRequest, value));

        categoryRepository.save(category
                .orElseThrow(() -> new NotFoundException(format("Category with id: %s not found", id))));
    }

    @Override
    public void deleteById(Integer id) {
        categoryRepository.deleteById(id);
    }
}
