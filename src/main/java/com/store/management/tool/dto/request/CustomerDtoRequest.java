package com.store.management.tool.dto.request;

import com.store.management.tool.validation.Required;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import static com.store.management.tool.exception.ErrorCode.*;

@Getter
@Builder(setterPrefix = "with")
@Jacksonized
public class CustomerDtoRequest {

    @Required(errorCode = MISSING_USERNAME)
    private String username;

    @Required(errorCode = MISSING_EMAIL)
    private String email;
}
