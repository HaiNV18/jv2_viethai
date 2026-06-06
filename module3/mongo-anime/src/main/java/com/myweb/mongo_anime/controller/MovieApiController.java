package com.myweb.mongo_anime.controller;

import com.myweb.mongo_anime.model.Movie;
import com.myweb.mongo_anime.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:8088") //cho phép ứng dụng ở port khác gọi API này
public class MovieApiController {

    @Autowired
    public MovieService movieService;

    @PostMapping("/movie/insert")
    public String insertMovie(Movie req) {
        movieService.saveMovie(req);
        return "success";
        // return "redirect:/admin/movie/list"; // Phải dùng @Controller mới redirect được
    }

    @GetMapping("/movie/list")
    public ResponseEntity<List<Movie>> listMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @GetMapping("/movie/top-movies")
    public ResponseEntity<Page<Movie>> getTop10Movies(){
        Page<Movie> allMovies = movieService.findAllPagination(PageRequest.of(0, 10));
        return new ResponseEntity<>(allMovies, HttpStatus.OK);
    }

    @GetMapping("/movie/detail/{id}")
    public ResponseEntity<Movie> getMovieDetail(@PathVariable String id) {
        Movie movie = movieService.findById(id);
        if (movie == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movie);
    }

}
