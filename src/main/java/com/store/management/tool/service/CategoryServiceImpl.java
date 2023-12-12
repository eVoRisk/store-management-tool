package com.store.management.tool.service;

import com.store.management.tool.dto.CategoryDto;
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
    public CategoryDto add(CategoryDto categoryDto) {
        categoryRepository.save(modelMapper.map(categoryDto, Category.class));

        return categoryDto;
    }

    @Override
    public Category getById(Integer id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(format("Category with id: %s not found", id)));
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void update(CategoryDto categoryDto, Integer id) {
        Optional<Category> category = categoryRepository.findById(id);
        category.ifPresent(value -> modelMapper.map(categoryDto, value));

        categoryRepository.save(category
                .orElseThrow(() -> new NotFoundException(format("Category with id: %s not found", id))));
    }

    @Override
    public void deleteById(Integer id) {
        categoryRepository.deleteById(id);
    }
}
