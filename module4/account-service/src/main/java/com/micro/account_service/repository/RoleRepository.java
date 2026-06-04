package com.micro.account_service.repository;

import com.micro.account_service.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
    Role findByRoleId(String roleId);
}
