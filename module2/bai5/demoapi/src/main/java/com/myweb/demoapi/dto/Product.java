package com.myweb.demoapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    // DTO: Data transfer object (đối tượng truyền dữ liệu)
    private String name;
    private Integer price;
    private String color;
}
