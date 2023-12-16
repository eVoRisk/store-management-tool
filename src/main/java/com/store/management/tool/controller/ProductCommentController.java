package com.store.management.tool.controller;

import com.store.management.tool.dto.request.ProductCommentDtoRequest;
import com.store.management.tool.dto.response.ProductCommentDtoResponse;
import com.store.management.tool.service.ProductCommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("store/api/v1")
public class ProductCommentController {


    private final ProductCommentService productCommentService;

    @PostMapping("/productcomment/add")
    public ResponseEntity<ProductCommentDtoResponse> addProductComment(@RequestBody @Valid final ProductCommentDtoRequest productCommentDtoRequest) {
        var response = productCommentService.add(productCommentDtoRequest);

        return ResponseEntity
                .ok()
                .body(response);
    }

    @GetMapping("/productcomment/{id}")
    public ResponseEntity<ProductCommentDtoResponse> retrieveProductCommentById(@PathVariable final Integer id) {
        var response = productCommentService.getById(id);

        return ResponseEntity
                .ok()
                .body(response);
    }

    @GetMapping("/productcomments")
    public ResponseEntity<List<ProductCommentDtoResponse>> retrieveProductComments() {
        var response = productCommentService.getAll();

        return ResponseEntity
                .ok()
                .body(response);
    }

    @PutMapping("/productcomment/{id}")
    public ResponseEntity<ProductCommentDtoRequest> updateProductComment(@RequestBody @Valid final ProductCommentDtoRequest productCommentDtoRequest, @PathVariable final Integer id) {
        productCommentService.update(productCommentDtoRequest, id);

        return ResponseEntity
                .ok()
                .body(productCommentDtoRequest);
    }

    @DeleteMapping("productcomment/{id}")
    public ResponseEntity<?> deleteProductComment(@PathVariable final Integer id) {
        productCommentService.deleteById(id);

        return ResponseEntity
                .ok()
                .build();
    }
}
