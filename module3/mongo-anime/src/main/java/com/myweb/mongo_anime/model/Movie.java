package com.myweb.mongo_anime.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "movies")
public class Movie {
    @Field("Title")
    private String title;
    @Field("Year")
    private String year;
    @Field("Director")
    private String director;
    @Field("Overview")
    private String overview;
    @Field("Release_Date")
    private String releaseDate;
    @Field("Popularity")
    private Double popularity;
}

// Slide bai 3
//db.movies.insertOne({
//    title: "Inception",
//            year: 2010,
//            genre: ["Action", "Sci-Fi", "Thriller"],
//    director: "Christopher Nolan",
//            rating: 8.8
//});