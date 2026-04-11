package com.myweb.bai9.service;

import com.myweb.bai9.dto.CategoryDto;
import com.myweb.bai9.dto.ProductDto;
import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    public List<CategoryDto> getlistCatByJsonNode(JsonNode data) {
        List<CategoryDto> listCat = new ArrayList<>();
        for (JsonNode item : data) {
            CategoryDto dto = new CategoryDto();
            dto.setName(item.asString());
            listCat.add(dto);
        }
        return listCat;
    }
}
