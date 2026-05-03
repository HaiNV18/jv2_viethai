package com.myweb.restaurant.model;

import lombok.Data;

import java.util.List;

@Data
public class Address {
    private String building;
    private List<Double> coord;
    private String street;
    private String zipcode;
}
