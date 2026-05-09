package com.myweb.mongo_anime.service;

import com.myweb.mongo_anime.model.Movie;
import com.myweb.mongo_anime.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    public MovieRepository movieRepo;

    public Page<Movie> findAllPagination(Pageable pageable){
        return movieRepo.findAll(pageable);
    }
}
