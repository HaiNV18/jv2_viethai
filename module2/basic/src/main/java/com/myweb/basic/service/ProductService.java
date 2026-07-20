package com.myweb.basic.service;

import com.myweb.basic.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    public List<ProductDto> getListProduct() {
        List<ProductDto> listProducts = new ArrayList<>();

        ProductDto productApple = new ProductDto();
        productApple.setId(1L);
        productApple.setName("Apple");
        listProducts.add(productApple);

        ProductDto productSamsung = new ProductDto();
        productSamsung.setId(2L);
        productSamsung.setName("Samsung");
        listProducts.add(productSamsung);

        return listProducts;
    }
}
