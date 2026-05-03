package com.myweb.restaurant.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "items")
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private String restaurantId;
    private String name;
    private String description;
    private Double price;
    private String category;
}
