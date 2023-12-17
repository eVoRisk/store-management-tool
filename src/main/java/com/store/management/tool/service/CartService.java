package com.store.management.tool.service;

import com.store.management.tool.dto.request.CustomerDtoRequest;
import com.store.management.tool.dto.response.CartDtoResponse;

public interface CartService {

    CartDtoResponse create(CustomerDtoRequest customerDtoRequest);

    CartDtoResponse addProduct(Integer cartId, Integer productId);

    CartDtoResponse removeProduct(Integer cartId, Integer productId);

    CartDtoResponse getById(Integer id);

    void deleteById(Integer id);
}
