package com.store.management.tool.service;

import com.store.management.tool.dto.request.CustomerDtoRequest;
import com.store.management.tool.dto.response.CartDtoResponse;
import com.store.management.tool.dto.response.ProductDtoResponse;
import com.store.management.tool.exception.NotFoundException;
import com.store.management.tool.exception.StoreManagementToolException;
import com.store.management.tool.model.Cart;
import com.store.management.tool.model.CartItem;
import com.store.management.tool.repository.CartItemRepository;
import com.store.management.tool.repository.CartRepository;
import com.store.management.tool.repository.CustomerRepository;
import com.store.management.tool.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import static com.store.management.tool.exception.ErrorCode.PRODUCT_ALREADY_IN_CART;
import static java.lang.String.format;

@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final CartItemRepository cartItemRepository;

    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public CartDtoResponse create(CustomerDtoRequest customerDtoRequest) {
        var email = customerDtoRequest.getEmail();
        var customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(format("Customer with email: %s not found", email)));

        var cart = new Cart();
        cart.setCustomer(customer);

        return modelMapper.map(cartRepository.save(cart), CartDtoResponse.class);
    }

    @Override
    @Transactional
    public CartDtoResponse addProduct(Integer cartId, Integer productId) {
        var product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException(format("Product with id: %s not found", productId)));

        var cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new NotFoundException(format("Cart with id: %s not found", cartId)));

        cartItemRepository.findCartItemByCartIdAndProductId(cartId, productId)
                .ifPresent(c -> {
                    throw new StoreManagementToolException(PRODUCT_ALREADY_IN_CART);
                });

        var cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);

        cartItemRepository.save(cartItem);

        return getCartDtoResponse(cart);
    }

    @Override
    @Transactional
    public CartDtoResponse removeProduct(Integer cartId, Integer productId) {
        var cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new NotFoundException(format("Cart with id: %s not found", cartId)));

        cartItemRepository.findCartItemByCartIdAndProductId(cartId, productId)
                .orElseThrow(() -> new NotFoundException(format("Product with id: %s not found", productId)));

        cartItemRepository.deleteCartItemByCartIdAndProductId(cartId, productId);

        return getCartDtoResponse(cart);
    }

    @Override
    @Transactional
    public CartDtoResponse getById(Integer id) {
        var cart = cartRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(format("Cart with id: %s not found", id)));

        return getCartDtoResponse(cart);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        cartItemRepository.deleteCartItemByCartId(id);
        cartRepository.deleteById(id);
    }

    @NotNull
    private CartDtoResponse getCartDtoResponse(Cart cart) {
        var cartDtoResponse = modelMapper.map(cart, CartDtoResponse.class);
        var products = cart.getCartItems().stream()
                .map(c -> modelMapper.map(c.getProduct(), ProductDtoResponse.class))
                .toList();

        cartDtoResponse.setProducts(products);

        return cartDtoResponse;
    }
}
