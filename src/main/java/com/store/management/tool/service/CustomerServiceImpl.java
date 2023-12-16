package com.store.management.tool.service;

import com.store.management.tool.dto.request.CustomerDtoRequest;
import com.store.management.tool.dto.response.CustomerDtoResponse;
import com.store.management.tool.exception.NotFoundException;
import com.store.management.tool.model.Customer;
import com.store.management.tool.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Override
    public CustomerDtoResponse add(CustomerDtoRequest customerDtoRequest) {
        var customer = customerRepository.save(modelMapper.map(customerDtoRequest, Customer.class));

        return modelMapper.map(customer, CustomerDtoResponse.class);
    }

    @Override
    public CustomerDtoResponse getById(Integer id) {
        var customer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(format("Customer with id: %s not found", id)));

        return modelMapper.map(customer, CustomerDtoResponse.class);
    }

    @Override
    public List<CustomerDtoResponse> getAll() {
        return customerRepository.findAll().stream()
                .map(customer -> modelMapper.map(customer, CustomerDtoResponse.class)).toList();
    }

    @Override
    public void update(CustomerDtoRequest customerDtoRequest, Integer id) {
        Optional<Customer> customer = customerRepository.findById(id);
        customer.ifPresent(value -> modelMapper.map(customerDtoRequest, value));

        customerRepository.save(customer
                .orElseThrow(() -> new NotFoundException(format("Customer with id: %s not found", id))));
    }

    @Override
    public void deleteById(Integer id) {
        customerRepository.deleteById(id);
    }
}
