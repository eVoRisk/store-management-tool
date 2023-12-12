package com.store.management.tool.service;

import com.store.management.tool.model.Cart;

public interface CartService {

    Cart getById(Integer id);

    void deleteById(Integer id);
}
