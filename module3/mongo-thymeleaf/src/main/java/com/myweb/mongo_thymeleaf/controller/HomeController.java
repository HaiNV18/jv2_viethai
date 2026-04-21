package com.myweb.mongo_thymeleaf.controller;

import com.myweb.mongo_thymeleaf.model.Movie;
import com.myweb.mongo_thymeleaf.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/home")
    public String showHomepage(Model model) {
        List<Movie> listMovie = movieService.getAllMovies();
        model.addAttribute("listMovie", listMovie);
        return "list-movie";
    }

    @GetMapping("/insert-movie")
    public String showInsertPage(Model model) {
        return "insert-movie";
    }

    // http://localhost:8080/api/v1/movie
    @PostMapping("/insert")
    public String insertMovie(Model model) {
        Movie movie = new Movie();
        return "insert-movie";
    }

    @GetMapping("/update-movie/{id}")
    public String showInsertPage(
            Model model,
            @PathVariable("id") String id
    ) {
        Movie movie = movieService.findById(id);
        model.addAttribute("movie", movie);
        model.addAttribute("error-message", "Found");
        if (movie == null) {
            model.addAttribute("error-message", "Movie Not Found");
        }
        return "update-movie";
    }

    @GetMapping("/search")
    public String searchMovie(
            Model model,
            @RequestParam String title
    ) {
        List<Movie> listMovie = movieService.searchByTitle(title);
        model.addAttribute("listMovie", listMovie);
        return "list-movie";
    }

    @GetMapping("/insert-update-movie")
    public String showInsertUpdatePage(
            Model model,
            @RequestParam("id") String id
    ) {
        Movie movie = movieService.findById(id);
        model.addAttribute("movie", movie);
        model.addAttribute("error-message", "Found");
        if (movie == null) {
            model.addAttribute("error-message", "Movie Not Found");
        }
        return "insert-update-movie";
    }

}
