package com.myweb.basic.controller;

import com.myweb.basic.service.ExternalApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tools.jackson.databind.JsonNode;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    public ExternalApiService externalApiService;

    @GetMapping("/product/list")
    public ResponseEntity<JsonNode> getProductList() {
        try {
            JsonNode data = externalApiService.fetchDataFromExternalApi("https://dummyjson.com/products");
            return ResponseEntity.ok(data);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
