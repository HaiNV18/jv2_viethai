package com.myweb.ecommerce.repository;

import com.myweb.ecommerce.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    Optional<Product> findByProductId(Integer productId);

    List<Product> findByBrand(String brand);

    Page<Product> findByStatus(Pageable pageable, String status);

    @Query("{ 'brand': ?0, 'name': { $regex: ?1, $options: 'i' } }")
    Page<Product> findByBrandAndKeyword(Pageable pageable, String brand, String keyword);

    @Query("{ 'name': { $regex: ?0, $options: 'i' } }")
    Page<Product> findByNameContainingIgnoreCase(Pageable pageable, String keyword);

    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);

    boolean existsByProductId(Integer productId);

    void deleteByProductId(Integer productId);

    @Query(value = "{}", sort = "{ 'Create_Date' : -1 }")
    List<Product> findNewestProducts();
}
