package com.myweb.ecommerce.repository;

import com.myweb.ecommerce.model.BillProduct;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillProductRepository extends MongoRepository<BillProduct, String> {

}
