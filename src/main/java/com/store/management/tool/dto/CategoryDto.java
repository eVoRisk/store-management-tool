package com.store.management.tool.dto;

import com.store.management.tool.validation.Required;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import static com.store.management.tool.exception.ErrorCode.MISSING_CATEGORY_NAME;

@Getter
@Builder(setterPrefix = "with")
@Jacksonized
public class CategoryDto {

    @Required(errorCode = MISSING_CATEGORY_NAME)
    private String name;
}
