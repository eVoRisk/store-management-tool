package com.store.management.tool.dto.response;

import com.store.management.tool.dto.request.CategoryDtoRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDtoResponse {

    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private CategoryDtoResponse category;
}
