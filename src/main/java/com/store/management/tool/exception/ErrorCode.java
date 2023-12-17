package com.store.management.tool.exception;

import lombok.Getter;

import java.util.Optional;

import static java.util.Arrays.stream;

@Getter
public enum ErrorCode {

    INTERNAL_ERROR("Internal error"),

    INVALID_INPUT("Request body is invalid"),

    PRODUCT_ALREADY_IN_CART("Product already in cart"),

    MISSING_MULTIPLE_FIELDS("Request body is missing multiple mandatory fields: %s"),
    MISSING_CATEGORY_NAME("No category name was submitted"),
    MISSING_PRODUCT_COMMENT_BODY("No product comment body was submitted"),
    MISSING_PRODUCT_NAME("No product name was submitted"),
    MISSING_PRODUCT_DESCRIPTION("No product description price was submitted"),
    MISSING_PRODUCT_PRICE("No product price was submitted"),
    MISSING_PRODUCT_STOCK("No product stock was submitted"),
    MISSING_PRODUCT_CATEGORY("No product category was submitted"),
    MISSING_USERNAME("No username was submitted"),
    MISSING_PASSWORD("No password was submitted"),
    MISSING_EMAIL("No email was submitted");

    private final String message;

    ErrorCode(final String message) {
        this.message = message;
    }

    public static Optional<ErrorCode> findValueByName(final String name) {
        return stream(values())
                .filter(code -> code.name().equals(name))
                .findFirst();
    }
}
