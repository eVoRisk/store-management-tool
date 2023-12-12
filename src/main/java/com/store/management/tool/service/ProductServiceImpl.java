package com.store.management.tool.service;

import com.store.management.tool.dto.ProductDto;
import com.store.management.tool.exception.NotFoundException;
import com.store.management.tool.model.Product;
import com.store.management.tool.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    public ProductDto add(ProductDto productDto) {
        productRepository.save(modelMapper.map(productDto, Product.class));

        return productDto;
    }

    @Override
    public Product getById(Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(format("Product with id: %s not found", id)));
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public void update(ProductDto productDto, Integer id) {
        Optional<Product> product = productRepository.findById(id);
        product.ifPresent(value -> modelMapper.map(productDto, value));

        productRepository.save(product
                .orElseThrow(() -> new NotFoundException(format("Product with id: %s not found", id))));
    }

    @Override
    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }
}
