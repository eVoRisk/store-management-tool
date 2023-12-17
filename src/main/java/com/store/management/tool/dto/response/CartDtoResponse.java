package com.store.management.tool.dto.response;

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
    private CustomerDtoResponse customer;
    private List<ProductDtoResponse> products;
}
