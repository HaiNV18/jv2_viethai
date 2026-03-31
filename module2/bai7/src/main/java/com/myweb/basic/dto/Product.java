package com.myweb.basic.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    public Integer id;
    public String name;
    public String description;
    public Integer price;
}
