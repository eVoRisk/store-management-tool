package com.store.management.tool.service;

import com.store.management.tool.dto.ProductCommentDto;
import com.store.management.tool.exception.NotFoundException;
import com.store.management.tool.model.ProductComment;
import com.store.management.tool.repository.ProductCommentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@RequiredArgsConstructor
@Service
public class ProductCommentServiceImpl implements ProductCommentService {

    private final ProductCommentRepository productCommentRepository;
    private final ModelMapper modelMapper;

    @Override
    public ProductCommentDto add(ProductCommentDto productCommentDto) {
        productCommentRepository.save(modelMapper.map(productCommentDto, ProductComment.class));

        return productCommentDto;
    }

    @Override
    public ProductComment getById(Integer id) {
        return productCommentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(format("Product comment with id: %s not found", id)));
    }

    @Override
    public List<ProductComment> getAll() {
        return productCommentRepository.findAll();
    }

    @Override
    public void update(ProductCommentDto productCommentDto, Integer id) {
        Optional<ProductComment> product = productCommentRepository.findById(id);
        product.ifPresent(value -> modelMapper.map(productCommentDto, value));

        productCommentRepository.save(product
                .orElseThrow(() -> new NotFoundException(format("Product comment with id: %s not found", id))));
    }

    @Override
    public void deleteById(Integer id) {
        productCommentRepository.deleteById(id);
    }
}
