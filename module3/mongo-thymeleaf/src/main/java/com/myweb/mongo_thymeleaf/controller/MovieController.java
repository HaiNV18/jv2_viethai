package com.myweb.mongo_thymeleaf.controller;

import com.myweb.mongo_thymeleaf.model.Movie;
import com.myweb.mongo_thymeleaf.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private org.springframework.data.mongodb.core.MongoTemplate mongoTemplate;

    @GetMapping("/debug")
    public Object debug() {
        return mongoTemplate.getDb().getName();
    }

    @GetMapping("/get-all")
    public List<Movie> getAllMovies(){
        return movieService.getAllMovies();
    }

    @GetMapping("/get-by-director/{director}")
    public List<Movie> getByDirector(@PathVariable("director") String director){
        return movieService.findByDirector(director);
    }

    @GetMapping("/get-by-year-title/{year}/{title}")
    public List<Movie> getByYearAndTitle(
            @PathVariable("year") Integer year,
            @PathVariable("title") String title
    ){
        return movieService.findByYearAndTitle(year,title);
    }

    @GetMapping("/get-by-rating-less-than-equal/{rating}")
    public List<Movie> getByRatingLessThanEqual(
            @PathVariable("rating") Double rating
    ){
        return  movieService.findByRatingLessThanEqual(rating);
    }

    @GetMapping("/get-by-year-between/{startYear}/{endYear}")
    public List<Movie> getByYearAndTitle(
            @PathVariable("startYear") Integer startYear,
            @PathVariable("endYear") Integer endYear
    ){
        return movieService.findByYearBetween(startYear,endYear);
    }

    @GetMapping("/search")
    public List<Movie> searchByTitle(@RequestParam String keyword){
        return movieService.searchByTitle(keyword);
    }

    @GetMapping("/find_title")
    public ResponseEntity<String> findByTitle(@RequestParam String title){
        try {
            Movie movie = movieService.findByTitle(title);
            return new ResponseEntity<String>(movie.getTitle(), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> createMovie(@RequestBody Movie movie) {
        Movie newMovie = movieService.createNewMovie(movie);
        return new ResponseEntity<>(newMovie.getId(), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateMovie(
            @RequestBody Movie movie) {
        Movie updated = movieService.updateMovie(movie);
        return new ResponseEntity<>(updated.getId(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> updateMovie(
            @PathVariable String id) {
        movieService.deleteMovie(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
