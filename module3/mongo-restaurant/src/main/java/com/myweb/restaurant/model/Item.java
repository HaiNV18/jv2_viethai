package com.myweb.restaurant.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "items")
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    private String id;

    @Field("item_id")
    @JsonProperty("item_id")
    private String itemId;

    @Field("restaurant_id")
    @JsonProperty("restaurant_id")
    private String restaurantId;

    private String name;
    private String description;
    private Double price;
    private String category;
}