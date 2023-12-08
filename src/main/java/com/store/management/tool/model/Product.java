package com.store.management.tool.model;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Getter
@Builder(setterPrefix = "with")
@Jacksonized
public class Product {
    private String id;
    private String name;
    private BigDecimal price;
    private String description;
    private Category category;
}
