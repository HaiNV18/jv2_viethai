package com.myweb.mongo_anime.controller;

import com.myweb.mongo_anime.model.Movie;
import com.myweb.mongo_anime.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class MovieApiController {

    @Autowired
    public MovieService movieService;

    @PostMapping("/movie/insert")
    public String insertMovie(@RequestBody Movie req) {
        movieService.saveMovie(req);
        return "success";
    }




}
