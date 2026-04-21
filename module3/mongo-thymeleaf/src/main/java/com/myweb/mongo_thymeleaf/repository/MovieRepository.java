package com.myweb.mongo_thymeleaf.repository;

import com.myweb.mongo_thymeleaf.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MovieRepository extends MongoRepository<Movie, String> {

    List<Movie> findByDirector(String director);

    List<Movie> findByYearAndTitle(Integer year, String title);

    List<Movie> findByRatingLessThanEqual(Double rating);

    List<Movie> findByYearBetween(Integer startYear, Integer endYear);

    List<Movie> findByTitleContainingIgnoreCase(String keyword);

    Movie findByTitle(String title);
}
