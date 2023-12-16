package com.store.management.tool.controller;

import com.store.management.tool.dto.request.ProductDtoRequest;
import com.store.management.tool.dto.response.ProductDtoResponse;
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
    public ResponseEntity<ProductDtoResponse> addProduct(@RequestBody @Valid final ProductDtoRequest productDtoRequest) {
        var response = productService.add(productDtoRequest);

        return ResponseEntity
                .ok()
                .body(response);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDtoResponse> retrieveProductById(@PathVariable final Integer id) {
        var response = productService.getById(id);

        return ResponseEntity
                .ok()
                .body(response);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDtoResponse>> retrieveProducts() {
        var response = productService.getAll();

        return ResponseEntity
                .ok()
                .body(response);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<ProductDtoRequest> updateProduct(@RequestBody @Valid final ProductDtoRequest productDto, @PathVariable final Integer id) {
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
