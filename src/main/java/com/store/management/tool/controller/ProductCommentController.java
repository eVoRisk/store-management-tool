package com.store.management.tool.controller;

import com.store.management.tool.dto.ProductCommentDto;
import com.store.management.tool.model.ProductComment;
import com.store.management.tool.service.ProductCommentService;
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
    public ResponseEntity<ProductCommentDto> addProductComment(@RequestBody final ProductCommentDto productCommentDto) {
        var response = productCommentService.add(productCommentDto);

        return ResponseEntity
                .ok()
                .body(response);
    }

    @GetMapping("/productcomment/{id}")
    public ResponseEntity<ProductComment> retrieveProductCommentById(@PathVariable final Integer id) {
        var response = productCommentService.getById(id);

        return ResponseEntity
                .ok()
                .body(response);
    }

    @GetMapping("/productcomments")
    public ResponseEntity<List<ProductComment>> retrieveProductComments() {
        var response = productCommentService.getAll();

        return ResponseEntity
                .ok()
                .body(response);
    }

    @PutMapping("/productcomment/{id}")
    public ResponseEntity<ProductCommentDto> updateProductComment(@RequestBody final ProductCommentDto productCommentDto, @PathVariable final Integer id) {
        productCommentService.update(productCommentDto, id);

        return ResponseEntity
                .ok()
                .body(productCommentDto);
    }

    @DeleteMapping("productcomment/{id}")
    public ResponseEntity<?> deleteProductComment(@PathVariable final Integer id) {
        productCommentService.deleteById(id);

        return ResponseEntity
                .ok()
                .build();
    }
}
