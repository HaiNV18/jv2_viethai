package com.micro.account_service.repository;

import com.micro.account_service.model.UserRole;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends MongoRepository<UserRole, String> {
    List<UserRole> findByUserId(String userId);
}
