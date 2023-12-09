package com.store.management.tool.controller;

import com.store.management.tool.model.Product;
import com.store.management.tool.service.FakeStoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("fakestore/api/v1")
public class FakeStoreController {

    private final FakeStoreService fakeStoreService;

    public FakeStoreController(final FakeStoreService fakeStoreService) {
        this.fakeStoreService = fakeStoreService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> retrieveProducts() {
        var response = fakeStoreService.products();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/products/categories")
    public ResponseEntity<List<String>> retrieveCategories() {
        var response = fakeStoreService.categories();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/products/category/{categoryName}")
    public ResponseEntity<List<Product>> retrieveProductsByCategory(@PathVariable("categoryName") final String categoryName) {
        var response = fakeStoreService.productsByCategory(categoryName);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
