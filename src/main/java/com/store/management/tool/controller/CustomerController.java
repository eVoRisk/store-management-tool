package com.store.management.tool.controller;

import com.store.management.tool.dto.request.CustomerDtoRequest;
import com.store.management.tool.dto.response.CustomerDtoResponse;
import com.store.management.tool.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("store/api/v1")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/customer/add")
    public ResponseEntity<CustomerDtoResponse> addCustomer(@RequestBody @Valid final CustomerDtoRequest customerDtoRequest) {
        var response = customerService.add(customerDtoRequest);

        return ResponseEntity
                .ok()
                .body(response);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<CustomerDtoResponse> retrieveCustomerById(@PathVariable final Integer id) {
        var response = customerService.getById(id);

        return ResponseEntity
                .ok()
                .body(response);
    }

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDtoResponse>> retrieveCustomers() {
        var response = customerService.getAll();

        return ResponseEntity
                .ok()
                .body(response);
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<CustomerDtoRequest> updateCustomer(@RequestBody @Valid final CustomerDtoRequest customerDtoRequest, @PathVariable final Integer id) {
        customerService.update(customerDtoRequest, id);

        return ResponseEntity
                .ok()
                .body(customerDtoRequest);
    }

    @DeleteMapping("customer/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable final Integer id) {
        customerService.deleteById(id);

        return ResponseEntity
                .ok()
                .build();
    }
}
