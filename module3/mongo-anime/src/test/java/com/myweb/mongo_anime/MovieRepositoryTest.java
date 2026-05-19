package com.myweb.mongo_anime;

import com.myweb.mongo_anime.model.Movie;
import com.myweb.mongo_anime.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.mongodb.test.autoconfigure.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@ActiveProfiles("test")
public class MovieRepositoryTest {

//    @Autowired
//    private MovieRepository movieRepository;
//
//    @Test
//    void findHighlyRated_returnsMoviesAboveThreshold() {
//
//        // xoá dữ liệu cũ
//        movieRepository.deleteAll();
//
//        // thêm dữ liệu test
//        movieRepository.save(new Movie(6.0));
//        movieRepository.save(new Movie(9.5));
//
//        // chạy query
//        List<Movie> result = movieRepository.findHighlyRated(8.0);
//
//        // kiểm tra kết quả
//        assertThat(result).hasSize(1);
//    }
}
