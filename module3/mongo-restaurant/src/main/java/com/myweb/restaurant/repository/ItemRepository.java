package com.myweb.restaurant.repository;

import com.myweb.restaurant.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends MongoRepository<Item, String> {
    Optional<Item> findByRestaurantId(String borough);
}
