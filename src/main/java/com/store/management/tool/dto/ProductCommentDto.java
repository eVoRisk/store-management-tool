package com.store.management.tool.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@Getter
@Builder(setterPrefix = "with")
@Jacksonized
public class ProductCommentDto {

    private String body;
    private Integer rating;
    private LocalDateTime createDate;
}
