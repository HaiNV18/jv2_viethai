package com.myweb.mongo_thymeleaf.service;

import com.myweb.mongo_thymeleaf.model.Movie;
import com.myweb.mongo_thymeleaf.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    public Movie findById(String id){
        Optional<Movie> movie = movieRepository.findById(id);

        // Kiem tra ket qua
        if (movie.isPresent()){
            // Có kết quả
            Movie result = movie.get();
            return result;
        }

        // Khong co ket qua
        Movie movie1 = new Movie();
        return movie1;
    }

    public List<Movie> findByDirector(String director){
        return movieRepository.findByDirector(director);
    }

    public List<Movie> findByYearAndTitle(Integer year, String title){
        return movieRepository.findByYearAndTitle(year,title);
    }

    public List<Movie> findByRatingLessThanEqual(Double rating){
        return movieRepository.findByRatingLessThanEqual(rating);
    }

    public List<Movie> findByYearBetween(Integer startYear, Integer endYear){
        return movieRepository.findByYearBetween(startYear, endYear);
    }

    public List<Movie> searchByTitle(String keyword){
        return movieRepository.findByTitleContainingIgnoreCase(keyword);
    }

    public Movie findByTitle(String title) throws Exception{
        Movie movie = movieRepository.findByTitle(title);
        if (Objects.isNull(movie)){
            throw new Exception("Movie not found");
        }
        return movie;
    }

    public Movie createNewMovie(Movie params){
        return movieRepository.save(params);
    }

    public Movie updateMovie(Movie params){

        String id = params.getId();
//        Movie movieExist = movieRepository.findById(id).orElse(null);
        Movie existing = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + id));

        existing.setTitle(params.getTitle());
        existing.setGenre(params.getGenre());
        existing.setDirector(params.getDirector());
        existing.setYear(params.getYear());
        existing.setRating(params.getRating());

        return movieRepository.save(existing);
    }

    public void deleteMovie(String id){
        Movie existing = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + id));
        movieRepository.delete(existing);
    }
}
