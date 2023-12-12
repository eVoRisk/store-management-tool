package com.store.management.tool.service;

import com.store.management.tool.dto.ProductDto;
import com.store.management.tool.model.Product;

import java.util.List;

public interface ProductService {

    ProductDto add(ProductDto productDto);

    Product getById(Integer id);

    List<Product> getAll();

    void update(ProductDto productDto, Integer id);

    void deleteById(Integer id);
}
