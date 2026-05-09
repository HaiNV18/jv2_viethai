package com.myweb.restaurant.repository;

import com.myweb.restaurant.model.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends MongoRepository<Restaurant, String> {
    Optional<Restaurant> findByRestaurantId(String borough);
    Restaurant findFirstByRestaurantId(String restaurantId);
    List<Restaurant> findByBorough(String borough);

    Page<Restaurant> findByNameContainingIgnoreCase(
            String keyword,
            Pageable pageable
    );

    @Query("""
        {
           'name': { $regex: ?0, $options: 'i' },
           'borough': { $regex: ?1, $options: 'i' }
        }
""")
    Page<Restaurant> searchRestaurant(
            String keyword,
            String borough,
            Pageable pageable
    );
}
