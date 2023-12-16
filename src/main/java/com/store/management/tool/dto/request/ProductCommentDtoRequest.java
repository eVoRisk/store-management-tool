package com.store.management.tool.dto.request;

import com.store.management.tool.validation.Required;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

import static com.store.management.tool.exception.ErrorCode.MISSING_PRODUCT_COMMENT_BODY;

@Getter
@Setter
@Builder(setterPrefix = "with")
@Jacksonized
public class ProductCommentDtoRequest {

    @Required(errorCode = MISSING_PRODUCT_COMMENT_BODY)
    private String body;

    private LocalDateTime createDate;
}
