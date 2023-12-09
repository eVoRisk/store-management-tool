package com.store.management.tool.model;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder(setterPrefix = "with")
@Jacksonized
public class Rating {

    private Long rate;
    private Integer count;
}
