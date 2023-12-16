package com.store.management.tool.dto.fakestore;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Getter
@Builder(setterPrefix = "with")
@Jacksonized
public class ProductDto {

    private Integer id;
    private String title;
    private BigDecimal price;
    private String description;
    private String category;
    private String image;
    private RatingDto ratingDto;
}
