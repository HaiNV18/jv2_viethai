package com.myweb.restaurant.repository;

import com.myweb.restaurant.model.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends MongoRepository<Restaurant, String> {
    Optional<Restaurant> findByRestaurantId(String borough);
    Restaurant findFirstByRestaurantId(String restaurantId);
    List<Restaurant> findByBorough(String borough);
}
