package com.store.management.tool.service;

import com.store.management.tool.dto.request.ProductDtoRequest;
import com.store.management.tool.dto.response.ProductDtoResponse;
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
    public ProductDtoResponse add(ProductDtoRequest productDto) {
        var product = productRepository.save(modelMapper.map(productDto, Product.class));

        return modelMapper.map(product, ProductDtoResponse.class);
    }

    @Override
    public ProductDtoResponse getById(Integer id) {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(format("Product with id: %s not found", id)));

        return modelMapper.map(product, ProductDtoResponse.class);
    }

    @Override
    public List<ProductDtoResponse> getAll() {
        return productRepository.findAll().stream()
                .map(product -> modelMapper.map(product, ProductDtoResponse.class)).toList();
    }

    @Override
    public void update(ProductDtoRequest productDto, Integer id) {
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
