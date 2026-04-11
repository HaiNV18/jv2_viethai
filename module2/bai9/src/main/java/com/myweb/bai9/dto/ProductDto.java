package com.myweb.bai9.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductDto {
    public Integer id;
    public String name;
    public String description;
    public String img;
    public Integer price;
}
