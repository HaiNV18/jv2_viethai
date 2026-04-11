package com.myweb.bai9.controller;

import com.myweb.bai9.service.ExternalApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tools.jackson.databind.JsonNode;

@RestController
public class ProductController {

    @Autowired
    public ExternalApiService externalApiService;

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

    @PostMapping(value = "/product/add_product")
    public ResponseEntity<String> processAddData(@RequestBody Object dataParams) {
        System.out.println(dataParams);	//in dữ liệu nhận được
        return new ResponseEntity<>("Data is received", HttpStatus.OK);
    }
}
