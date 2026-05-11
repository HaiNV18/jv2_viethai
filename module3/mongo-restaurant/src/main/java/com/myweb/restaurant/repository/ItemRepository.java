package com.myweb.restaurant.repository;

import com.myweb.restaurant.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends MongoRepository<Item, String>, ItemRepositoryCustom {
    List<Item> findByRestaurantId(String restaurantId);
    Optional<Item> findByItemId(String itemId);

    @Query("""
        {
           'name': { $regex: ?0, $options: 'i' }
        }
    """)
    Page<Item> searchItem(
            String name,
            Pageable pageable
    );
}
