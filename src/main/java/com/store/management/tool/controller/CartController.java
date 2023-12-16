package com.store.management.tool.controller;

import com.store.management.tool.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("store/api/v1")
public class CartController {


    private final CartService cartService;

    @GetMapping("/cart/{id}")
    public ResponseEntity<?> retrieveCartById(@PathVariable final Integer id) {
        var response = cartService.getById(id);

        return ResponseEntity
                .ok()
                .body(response);
    }

    @DeleteMapping("cart/{id}")
    public ResponseEntity<?> deleteCart(@PathVariable final Integer id) {
        cartService.deleteById(id);

        return ResponseEntity
                .ok()
                .build();
    }
}
