package com.myweb.ecommerce.repository;

import com.myweb.ecommerce.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    Optional<Product> findByProductId(Integer productId);

    List<Product> findByBrand(String brand);

    List<Product> findByNameContainingIgnoreCase(String keyword);

    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);

    boolean existsByProductId(Integer productId);

    void deleteByProductId(Integer productId);

    @Query(value = "{}", sort = "{ 'Create_Date' : -1 }")
    List<Product> findNewestProducts();
}
