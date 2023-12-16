package com.store.management.tool.dto.response;

import com.store.management.tool.model.Customer;
import com.store.management.tool.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartDtoResponse {

    private Integer id;
    private Customer customer;
    private List<ProductDtoResponse> products;
}
