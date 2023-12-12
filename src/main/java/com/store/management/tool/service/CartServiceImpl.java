package com.store.management.tool.service;

import com.store.management.tool.exception.NotFoundException;
import com.store.management.tool.model.Cart;
import com.store.management.tool.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Override
    public Cart getById(Integer id) {
        return cartRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(format("Cart with id: %s not found", id)));
    }

    @Override
    public void deleteById(Integer id) {
        cartRepository.deleteById(id);
    }
}
