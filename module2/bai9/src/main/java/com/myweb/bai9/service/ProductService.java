package com.myweb.bai9.service;

import com.myweb.bai9.dto.ProductDto;
import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

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
}
