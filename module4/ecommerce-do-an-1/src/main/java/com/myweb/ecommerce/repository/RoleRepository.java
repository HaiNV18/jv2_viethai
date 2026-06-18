package com.myweb.ecommerce.repository;

import com.myweb.ecommerce.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
    Role findByRoleId(String roleId);
}
