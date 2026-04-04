package com.myweb.demoapi.service;

import com.myweb.demoapi.dto.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    public List<Product> getListProduct() {
        List<Product> listProduct = new ArrayList<>();

        Product product1 = Product.builder()
                .id(1)
                .name("Samsung")
                .price(1000000)
                .color("red")
                .build();
        listProduct.add(product1);

        Product product2 = Product.builder()
                .id(2)
                .name("iPhone")
                .price(2000000)
                .color("black")
                .build();
        listProduct.add(product2);

        Product product3 = Product.builder()
                .id(3)
                .name("OPPO")
                .price(1500000)
                .color("black")
                .build();
        listProduct.add(product3);

        Product product4 = Product.builder()
                .id(4)
                .name("Huawei")
                .price(1200000)
                .color("white")
                .build();
        listProduct.add(product4);

        return listProduct;
    }

    public Product getProductById(List<Product> listProducts, Integer id) {
        Product result = Product.builder()
                .id(null)
                .name(null)
                .price(null)
                .color(null)
                .build();

        for (int i=0; i<listProducts.size(); i++) {
            Product prod = listProducts.get(i);
            if (prod.getId() == id) {
                return prod;
            }
        }
        return result;
    }
}
