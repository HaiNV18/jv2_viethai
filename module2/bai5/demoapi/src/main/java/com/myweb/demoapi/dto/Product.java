package com.myweb.demoapi.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Product {
    // DTO: Data transfer object (đối tượng truyền dữ liệu)
    private Integer id;
//    @NotBlank(message = "Name is required")
    private String name;
    private Integer price;
    private String color;
}
