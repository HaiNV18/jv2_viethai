package com.myweb.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDto {
    private String id;
    private String name;
    private Double price;
    private String thumbnail;
    private Integer qty;
}