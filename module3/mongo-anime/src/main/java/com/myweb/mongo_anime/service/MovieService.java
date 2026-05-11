package com.myweb.mongo_anime.service;

import com.myweb.mongo_anime.model.Movie;
import com.myweb.mongo_anime.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    public MovieRepository movieRepo;

    public Page<Movie> findAllPagination(Pageable pageable){
        return movieRepo.findAll(pageable);
    }

    public Integer countMovies() {
        List<Movie> movies = movieRepo.findAll();
        Integer count = (int) movies.stream().count();
        return count;
    }

    public Map<String, Long> getAndGroupMoviesByLanguage() {
        List<Movie> movies = movieRepo.findAll();	//lấy tất cả dữ liệu

        //gom nhóm theo language và đếm số lượng trong từng nhóm
        return movies.stream()
                .filter(m -> m.getOriginal_language() != null)
                .collect(Collectors.groupingBy(
                        Movie::getOriginal_language,
                        Collectors.counting()
                ));
    }

}
