package com.store.management.tool.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Getter
@Builder(setterPrefix = "with")
@Jacksonized
public class ProductDto {

    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private CategoryDto category;
}
