package com.store.management.tool.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDtoResponse {

    private Integer id;
    private String username;
    private String email;
}
