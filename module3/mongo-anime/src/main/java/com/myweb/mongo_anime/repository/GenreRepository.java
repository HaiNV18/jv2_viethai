package com.myweb.mongo_anime.repository;

import com.myweb.mongo_anime.model.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends MongoRepository<Genre, String> {
    // findById
}
