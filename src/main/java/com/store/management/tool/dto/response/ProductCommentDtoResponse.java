package com.store.management.tool.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCommentDtoResponse {

    private Integer id;
    private String body;
    private LocalDateTime createDate;
}
