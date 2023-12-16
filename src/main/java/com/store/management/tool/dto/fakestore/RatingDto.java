package com.store.management.tool.dto.fakestore;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder(setterPrefix = "with")
@Jacksonized
public class RatingDto {

    private Long rate;
    private Integer count;
}
