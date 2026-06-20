package com.myweb.ecommerce.repository;

import com.myweb.ecommerce.model.Bill;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends MongoRepository<Bill, String> {

}
