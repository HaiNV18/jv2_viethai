package com.myweb.ecommerce.repository;

import com.myweb.ecommerce.model.UserRole;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends MongoRepository<UserRole, String> {
    List<UserRole> findByUserId(String userId);
}
