package com.myweb.mongo_anime.repository;

import com.myweb.mongo_anime.model.Logging;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoggingRepository extends MongoRepository<Logging, String> {

}
