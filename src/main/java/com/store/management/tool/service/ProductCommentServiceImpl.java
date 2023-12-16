package com.store.management.tool.service;

import com.store.management.tool.dto.request.ProductCommentDtoRequest;
import com.store.management.tool.dto.response.ProductCommentDtoResponse;
import com.store.management.tool.exception.NotFoundException;
import com.store.management.tool.model.ProductComment;
import com.store.management.tool.repository.CustomerRepository;
import com.store.management.tool.repository.ProductCommentRepository;
import com.store.management.tool.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;
import static java.time.LocalDateTime.now;

@RequiredArgsConstructor
@Service
public class ProductCommentServiceImpl implements ProductCommentService {

    private final ProductCommentRepository productCommentRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    private final ModelMapper modelMapper;
    private final Clock clock;

    @Override
    public ProductCommentDtoResponse add(ProductCommentDtoRequest productCommentDtoRequest, Integer productId, Integer customerId) {
        productCommentDtoRequest.setCreateDate(now(clock)); // TODO - refactor this, add custom mapper in order to set the date

        var product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException(format("Product with id: %s not found", productId)));
        var customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new NotFoundException(format("Customer with id: %s not found", customerId)));

        var productComment = modelMapper.map(productCommentDtoRequest, ProductComment.class);
        productComment.setProduct(product);
        productComment.setCustomer(customer);

        return modelMapper.map(productCommentRepository.save(productComment), ProductCommentDtoResponse.class);
    }

    @Override
    public ProductCommentDtoResponse getById(Integer id) {
        var productComment = productCommentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(format("Product comment with id: %s not found", id)));

        return modelMapper.map(productComment, ProductCommentDtoResponse.class);
    }

    @Override
    public void update(ProductCommentDtoRequest productCommentDtoRequest, Integer id) {
        Optional<ProductComment> product = productCommentRepository.findById(id);
        product.ifPresent(value -> modelMapper.map(productCommentDtoRequest, value));

        productCommentRepository.save(product
                .orElseThrow(() -> new NotFoundException(format("Product comment with id: %s not found", id))));
    }

    @Override
    public void deleteById(Integer id) {
        productCommentRepository.deleteById(id);
    }
}
