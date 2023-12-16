package com.store.management.tool.controller;

import com.store.management.tool.dto.request.ProductCommentDtoRequest;
import com.store.management.tool.dto.response.ProductCommentDtoResponse;
import com.store.management.tool.service.ProductCommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("store/api/v1")
public class ProductCommentController {


    private final ProductCommentService productCommentService;

    @PostMapping("/product/{productId}/comment/{customerId}/add")
    public ResponseEntity<ProductCommentDtoResponse> addProductComment(@RequestBody @Valid final ProductCommentDtoRequest productCommentDtoRequest,
                                                                       @PathVariable final Integer productId,
                                                                       @PathVariable final Integer customerId) {
        var response = productCommentService.add(productCommentDtoRequest, productId, customerId);

        return ResponseEntity
                .ok()
                .body(response);
    }

    @GetMapping("/product/comment/{id}")
    public ResponseEntity<ProductCommentDtoResponse> retrieveProductCommentById(@PathVariable final Integer id) {
        var response = productCommentService.getById(id);

        return ResponseEntity
                .ok()
                .body(response);
    }

    @PutMapping("/product/comment/{id}")
    public ResponseEntity<ProductCommentDtoRequest> updateProductComment(@RequestBody @Valid final ProductCommentDtoRequest productCommentDtoRequest, @PathVariable final Integer id) {
        productCommentService.update(productCommentDtoRequest, id);

        return ResponseEntity
                .ok()
                .body(productCommentDtoRequest);
    }

    @DeleteMapping("product/comment/{id}")
    public ResponseEntity<?> deleteProductComment(@PathVariable final Integer id) {
        productCommentService.deleteById(id);

        return ResponseEntity
                .ok()
                .build();
    }
}
