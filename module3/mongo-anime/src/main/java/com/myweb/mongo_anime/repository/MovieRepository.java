package com.myweb.mongo_anime.repository;

import com.myweb.mongo_anime.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MovieRepository extends MongoRepository<Movie, String> {

}
