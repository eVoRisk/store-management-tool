package com.store.management.tool.controller;

import com.store.management.tool.dto.CategoryDto;
import com.store.management.tool.model.Category;
import com.store.management.tool.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("store/api/v1")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/category/add")
    public ResponseEntity<CategoryDto> addCategory(@RequestBody final CategoryDto categoryDto) {
        var response = categoryService.add(categoryDto);

        return ResponseEntity
                .ok()
                .body(response);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Category> retrieveCategoryById(@PathVariable final Integer id) {
        var response = categoryService.getById(id);

        return ResponseEntity
                .ok()
                .body(response);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> retrieveCategories() {
        var response = categoryService.getAll();

        return ResponseEntity
                .ok()
                .body(response);
    }

    @PutMapping("/category/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody final CategoryDto categoryDto, @PathVariable final Integer id) {
        categoryService.update(categoryDto, id);

        return ResponseEntity
                .ok()
                .body(categoryDto);
    }

    @DeleteMapping("category/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable final Integer id) {
        categoryService.deleteById(id);

        return ResponseEntity
                .ok()
                .build();
    }
}
