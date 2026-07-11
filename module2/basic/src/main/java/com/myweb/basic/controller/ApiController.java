package com.myweb.basic.controller;

import com.myweb.basic.dto.ProductDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ApiController {

    @GetMapping("/hello-world")
    public ResponseEntity helloWorld() {
        String message = "Hello World";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity getAllProducts(){
        List<String> productNames = new ArrayList<>();
        productNames.add("Apple");
        productNames.add("Samsung");
        productNames.add("OPPO");
        productNames.add("Xiaomi");
        return new ResponseEntity<>(productNames, HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity getDetailProducts(@PathVariable Integer id){
        List<ProductDto> listProducts = new ArrayList<>();

        ProductDto productApple = new ProductDto();
        productApple.setId(1L);
        productApple.setName("Apple");
        listProducts.add(productApple);

        ProductDto productSamsung = new ProductDto();
        productSamsung.setId(2L);
        productSamsung.setName("Samsung");
        listProducts.add(productSamsung);

        for (ProductDto product : listProducts) {
            if (product.getId().equals(Long.valueOf(id))) {
                return ResponseEntity.ok(product);
            }
        }

        return new ResponseEntity<>(listProducts, HttpStatus.OK);
    }

    @PostMapping("/product/create")
    public ResponseEntity getDetailProducts(@RequestMapping Integer id){
        List<ProductDto> listProducts = new ArrayList<>();

        return new ResponseEntity<>(listProducts, HttpStatus.OK);
    }
}
