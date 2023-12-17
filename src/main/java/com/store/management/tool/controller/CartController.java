package com.store.management.tool.controller;

import com.store.management.tool.dto.request.CustomerDtoRequest;
import com.store.management.tool.dto.response.CartDtoResponse;
import com.store.management.tool.publish.PublishStatus;
import com.store.management.tool.publish.Publisher;
import com.store.management.tool.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.store.management.tool.publish.QueueName.CART_QUEUE;

@RequiredArgsConstructor
@RestController
@RequestMapping("store/api/v1")
public class CartController {

    private final static Logger LOGGER = LoggerFactory.getLogger(CartController.class);

    private final CartService cartService;
    private final Publisher publisher;

    @PostMapping("/cart/create")
    public ResponseEntity<?> createCartForCustomer(@RequestBody @Valid CustomerDtoRequest customerDtoRequest) {
        LOGGER.info("Received create cart request for user: {}", customerDtoRequest.getUsername());
        var response = cartService.create(customerDtoRequest);

        var status = publisher.publish(CART_QUEUE.toString(),
                String.format("cart: %s created for user: %s", response.getId(), customerDtoRequest.getUsername()));

        if (status != PublishStatus.ERROR) {
            LOGGER.info("Publisher successful published to Redis queue");
        }

        LOGGER.info("Sending successful response for creating cart: {}", response.getId());
        return ResponseEntity
                .ok()
                .body(response);
    }

    @PostMapping("/cart/{cartId}/add/product/{productId}")
    public ResponseEntity<CartDtoResponse> addProductToCart(@PathVariable final Integer cartId,
                                                            @PathVariable final Integer productId) {
        LOGGER.info("Received add product to cart request for product: {} and cart: {} ", cartId, productId);
        var response = cartService.addProduct(cartId, productId);

        LOGGER.info("Sending successful response for adding product: {} to cart: {}", cartId, productId);
        return ResponseEntity
                .ok()
                .body(response);
    }

    @PostMapping("/cart/{cartId}/remove/product/{productId}")
    public ResponseEntity<CartDtoResponse> removeProductFromCart(@PathVariable final Integer cartId,
                                                                 @PathVariable final Integer productId) {
        LOGGER.info("Received remove product from cart request for product: {} and cart: {} ", cartId, productId);
        var response = cartService.removeProduct(cartId, productId);

        LOGGER.info("Sending successful response for removing product: {} from cart: {}", cartId, productId);
        return ResponseEntity
                .ok()
                .body(response);
    }

    @GetMapping("/cart/{id}")
    public ResponseEntity<?> retrieveCartById(@PathVariable final Integer id) {
        LOGGER.info("Received retrieve request for cart: {} ", id);
        var response = cartService.getById(id);

        LOGGER.info("Sending successful response for retrieving cart: {}", id);
        return ResponseEntity
                .ok()
                .body(response);
    }

    @DeleteMapping("cart/{id}")
    public ResponseEntity<?> deleteCart(@PathVariable final Integer id) {
        LOGGER.info("Received delete request for cart: {} ", id);
        cartService.deleteById(id);

        LOGGER.info("Sending successful response for deleting cart: {}", id);
        return ResponseEntity
                .ok()
                .build();
    }
}
