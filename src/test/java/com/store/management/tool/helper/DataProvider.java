package com.store.management.tool.helper;

import com.store.management.tool.dto.request.CategoryDtoRequest;
import com.store.management.tool.dto.request.ProductDtoRequest;
import com.store.management.tool.model.Category;
import com.store.management.tool.model.Product;

import java.math.BigDecimal;

public class DataProvider {
    public static final String PRODUCT_NAME = "product name";
    public static final String PRODUCT_DESCRIPTION = "product description";
    public static final Integer STOCK = 10;
    public static final String CATEGORY_NAME = "category name";

    public static ProductDtoRequest defaultProductRequest() {
        return ProductDtoRequest.builder()
                .withName(PRODUCT_NAME)
                .withDescription(PRODUCT_DESCRIPTION)
                .withPrice(BigDecimal.TEN)
                .withStock(STOCK)
                .withCategory(defaultCategoryRequest())
                .build();
    }

    public static Product getProduct() {
        var product = new Product();

        product.setName(PRODUCT_NAME);
        product.setDescription(PRODUCT_DESCRIPTION);
        product.setPrice(BigDecimal.TEN);
        product.setStock(STOCK);
        product.setCategory(getCategory());

        return product;
    }

    public static CategoryDtoRequest defaultCategoryRequest() {
        return CategoryDtoRequest.builder()
                .withName(CATEGORY_NAME)
                .build();
    }

    public static Category getCategory() {
        var category = new Category();

        category.setName(CATEGORY_NAME);

        return category;
    }
}
