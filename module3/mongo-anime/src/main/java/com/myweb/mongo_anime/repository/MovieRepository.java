package com.myweb.mongo_anime.repository;

import com.myweb.mongo_anime.dto.ChartDTO;
import com.myweb.mongo_anime.model.Movie;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {
    @Query("{ 'VoteAverage' : { $gte: ?0 } }")
    List<Movie> findHighlyRated(double minRating);

//    @Aggregation()
//    List<ChartDTO> getMoviesByLanguage();

    List<Movie> findTop5ByOrderByVoteAverageDesc();

    List<Movie> findTop5ByOrderByReleaseDateDesc();

    List<Movie> findTop10ByOrderByVoteCountDesc();
}
