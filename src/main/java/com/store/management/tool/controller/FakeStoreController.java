package com.store.management.tool.controller;

import com.store.management.tool.dto.fakestore.ProductDto;
import com.store.management.tool.service.FakeStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("store/api/v1")
public class FakeStoreController {

    private final FakeStoreService fakeStoreService;

    @GetMapping("fakestore/products")
    public ResponseEntity<List<ProductDto>> retrieveProducts() {
        var response = fakeStoreService.products();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("fakestore/products/categories")
    public ResponseEntity<List<String>> retrieveCategories() {
        var response = fakeStoreService.categories();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("fakestore/products/category/{categoryName}")
    public ResponseEntity<List<ProductDto>> retrieveProductsByCategory(@PathVariable("categoryName") final String categoryName) {
        var response = fakeStoreService.productsByCategory(categoryName);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
