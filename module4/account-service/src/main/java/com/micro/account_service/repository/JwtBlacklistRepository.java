package com.micro.account_service.repository;

import com.micro.account_service.model.JwtBlacklist;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JwtBlacklistRepository extends MongoRepository<JwtBlacklist, String> {
    boolean existsByToken(String token);
}
