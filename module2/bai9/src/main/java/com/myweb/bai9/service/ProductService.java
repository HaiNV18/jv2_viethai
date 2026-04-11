package com.myweb.bai9.service;

import com.myweb.bai9.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tools.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    @Autowired
    private RestTemplate restTemplate;

    public List<ProductDto> getlistProductByJsonNode(JsonNode data) {
        List<ProductDto> listProduct = new ArrayList<>();
        JsonNode products = data.get("products");

        for (JsonNode item : products) {
            ProductDto product = new ProductDto(
                    item.get("id").asInt(),
                    item.get("title").asText(),
                    item.get("description").asText(),
                    item.get("thumbnail").asText(),
                    item.get("price").asInt()
            );
            listProduct.add(product);
        }
        return listProduct;
    }

    public ProductDto getProductByJsonNode(JsonNode item) {
        return new ProductDto(
                item.get("id").asInt(),
                item.get("title").asText(),
                item.get("description").asText(),
                item.get("thumbnail").asText(),
                item.get("price").asInt()
        );
    }

    public String addProduct(String title,
                             String description,
                             String price,
                             String category) {

        String url = "https://dummyjson.com/products/add";

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("title", title);
        requestBody.put("description", description);
        requestBody.put("price", Integer.parseInt(price));
        requestBody.put("category", category);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity =
                new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                String.class
        );
        System.out.println(response.getBody());
        return response.getBody();
    }

    public String updateProduct(Integer id,
                                String title,
                                String description,
                                String price,
                                String category) {

        String url = "https://dummyjson.com/products/" + id;

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("title", title);
        requestBody.put("description", description);
        requestBody.put("price", Integer.parseInt(price));
        requestBody.put("category", category);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity =
                new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                entity,
                String.class
        );
        System.out.println(response.getBody());
        return response.getBody();
    }
}
