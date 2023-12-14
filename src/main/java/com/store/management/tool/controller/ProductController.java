package com.store.management.tool.controller;

import com.store.management.tool.dto.ProductDto;
import com.store.management.tool.model.Product;
import com.store.management.tool.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("store/api/v1")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/product/add")
    public ResponseEntity<ProductDto> addProduct(@RequestBody @Valid final ProductDto productDto) {
        var response = productService.add(productDto);

        return ResponseEntity
                .ok()
                .body(response);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> retrieveProductById(@PathVariable final Integer id) {
        var response = productService.getById(id);

        return ResponseEntity
                .ok()
                .body(response);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> retrieveProducts() {
        var response = productService.getAll();

        return ResponseEntity
                .ok()
                .body(response);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody @Valid final ProductDto productDto, @PathVariable final Integer id) {
        productService.update(productDto, id);

        return ResponseEntity
                .ok()
                .body(productDto);
    }

    @DeleteMapping("product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable final Integer id) {
        productService.deleteById(id);

        return ResponseEntity
                .ok()
                .build();
    }
}
