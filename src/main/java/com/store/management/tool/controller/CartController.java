package com.store.management.tool.controller;

import com.store.management.tool.dto.request.CustomerDtoRequest;
import com.store.management.tool.dto.response.CartDtoResponse;
import com.store.management.tool.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("store/api/v1")
public class CartController {


    private final CartService cartService;

    @PostMapping("/cart/create")
    public ResponseEntity<?> createCartForCustomer(@RequestBody @Valid CustomerDtoRequest customerDtoRequest) {
        var response = cartService.create(customerDtoRequest);

        return ResponseEntity
                .ok()
                .body(response);
    }

    @PostMapping("/cart/{cartId}/add/product/{productId}")
    public ResponseEntity<CartDtoResponse> addProductToCart(@PathVariable final Integer cartId,
                                                            @PathVariable final Integer productId) {
        var response = cartService.addProduct(cartId, productId);

        return ResponseEntity
                .ok()
                .body(response);
    }

    @PostMapping("/cart/{cartId}/remove/product/{productId}")
    public ResponseEntity<CartDtoResponse> removeProductFromCart(@PathVariable final Integer cartId,
                                                                 @PathVariable final Integer productId) {
        var response = cartService.removeProduct(cartId, productId);

        return ResponseEntity
                .ok()
                .body(response);
    }

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
