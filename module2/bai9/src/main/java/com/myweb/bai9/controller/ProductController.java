package com.myweb.bai9.controller;

import com.myweb.bai9.service.ExternalApiService;
import com.myweb.bai9.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tools.jackson.databind.JsonNode;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ProductController {

    @Autowired
    public ExternalApiService externalApiService;

    @Autowired
    public ProductService productService;

    @GetMapping("/product/list")
    public ResponseEntity getProductList() {
        try {
            JsonNode data = externalApiService.fetchDataFromExternalApi("https://dummyjson.com/products");
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/product/categories")
    public ResponseEntity getProductCategories() {
        try {
            JsonNode data = externalApiService.fetchDataFromExternalApi("https://dummyjson.com/products/categories");
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
