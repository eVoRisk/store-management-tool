package com.store.management.tool.controller;

import com.store.management.tool.dto.request.CategoryDtoRequest;
import com.store.management.tool.dto.response.CategoryDtoResponse;
import com.store.management.tool.service.CategoryService;
import jakarta.validation.Valid;
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
    public ResponseEntity<CategoryDtoResponse> addCategory(@RequestBody @Valid final CategoryDtoRequest categoryDtoRequest) {
        var response = categoryService.add(categoryDtoRequest);

        return ResponseEntity
                .ok()
                .body(response);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryDtoResponse> retrieveCategoryById(@PathVariable final Integer id) {
        var response = categoryService.getById(id);

        return ResponseEntity
                .ok()
                .body(response);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDtoResponse>> retrieveCategories() {
        var response = categoryService.getAll();

        return ResponseEntity
                .ok()
                .body(response);
    }

    @PutMapping("/category/{id}")
    public ResponseEntity<CategoryDtoRequest> updateCategory(@RequestBody @Valid final CategoryDtoRequest categoryDtoRequest, @PathVariable final Integer id) {
        categoryService.update(categoryDtoRequest, id);

        return ResponseEntity
                .ok()
                .body(categoryDtoRequest);
    }

    @DeleteMapping("category/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable final Integer id) {
        categoryService.deleteById(id);

        return ResponseEntity
                .ok()
                .build();
    }
}
