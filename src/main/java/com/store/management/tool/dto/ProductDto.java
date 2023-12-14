package com.store.management.tool.dto;

import com.store.management.tool.validation.Required;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

import static com.store.management.tool.exception.ErrorCode.*;

@Getter
@Builder(setterPrefix = "with")
@Jacksonized
public class ProductDto {

    @Required(errorCode = MISSING_PRODUCT_NAME)
    private String name;

    @Required(errorCode = MISSING_PRODUCT_DESCRIPTION)
    private String description;

    @Required(errorCode = MISSING_PRODUCT_PRICE)
    @Positive
    private BigDecimal price;

    @Required(errorCode = MISSING_PRODUCT_STOCK)
    @Positive
    private Integer stock;

    @Required(errorCode = MISSING_PRODUCT_CATEGORY)
    private CategoryDto category;
}
