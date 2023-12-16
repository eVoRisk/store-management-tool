package com.store.management.tool.service;

import com.store.management.tool.dto.request.CustomerDtoRequest;
import com.store.management.tool.dto.response.CustomerDtoResponse;
import com.store.management.tool.dto.response.ProductCommentDtoResponse;

import java.util.List;

public interface CustomerService {

    CustomerDtoResponse add(CustomerDtoRequest customerDtoRequest);

    CustomerDtoResponse getById(Integer id);

    List<CustomerDtoResponse> getAll();

    List<ProductCommentDtoResponse> getAllComments(Integer id);

    void update(CustomerDtoRequest customerDtoRequest, Integer id);

    void deleteById(Integer id);
}
