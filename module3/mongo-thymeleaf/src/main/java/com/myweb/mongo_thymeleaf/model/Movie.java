package com.myweb.mongo_thymeleaf.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document (collection = "movies")
public class Movie {
    @Id
    private String id;
    private String title;
    private Integer year;
    private List<String> genre;
    private String director;
    private Double rating;
}



//[
//    {
//    "title": "Inception",
//    "year": 2010,
//    "genre": ["Action", "Sci-Fi", "Thriller"],
//    "director": "Christopher Nolan",
//    "rating": 8.8
//    },
//    {
//    "title": "The Shawshank Redemption",
//    "year": 1994,
//    "genre": ["Drama"],
//    "director": "Frank Darabont",
//    "rating": 9.3
//    }
//]