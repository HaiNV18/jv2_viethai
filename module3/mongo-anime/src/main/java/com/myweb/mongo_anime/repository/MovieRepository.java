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

    List<Movie> findTop5ByOrderByVoteAverageDesc(); // 5 phim danh gia cao nhat

    List<Movie> findTop5ByOrderByReleaseDateDesc(); // 5 phim phat hanh gan nhat

    List<Movie> findTop10ByOrderByVoteCountDesc(); // 10 phim pho bien nhat
}
