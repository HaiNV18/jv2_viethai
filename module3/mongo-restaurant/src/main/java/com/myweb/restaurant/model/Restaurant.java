package com.myweb.restaurant.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document(collection = "restaurants")
public class Restaurant {
    @Id
    private String id;

    @Field("restaurant_id")
    @JsonProperty("restaurant_id")
    private String restaurantId;
    private String name;
    private String borough;
    private String cuisine;
    private Address address;
    private List<Grade> grades;
    private List<Item> menuItems;
}
