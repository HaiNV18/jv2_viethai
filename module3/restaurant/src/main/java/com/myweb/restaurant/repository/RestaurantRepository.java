package com.myweb.restaurant.repository;

import com.myweb.restaurant.model.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RestaurantRepository extends MongoRepository<Restaurant, String> {
    List<Restaurant> findByBorough(String borough);
}
