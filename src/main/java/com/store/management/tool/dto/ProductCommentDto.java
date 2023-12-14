package com.store.management.tool.dto;

import com.store.management.tool.validation.Required;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

import static com.store.management.tool.exception.ErrorCode.MISSING_PRODUCT_COMMENT_BODY;

@Getter
@Builder(setterPrefix = "with")
@Jacksonized
public class ProductCommentDto {

    @Required(errorCode = MISSING_PRODUCT_COMMENT_BODY)
    private String body;

    private Integer rating;
    private LocalDateTime createDate;
}
