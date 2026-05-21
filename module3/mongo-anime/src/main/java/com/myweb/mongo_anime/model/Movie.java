package com.myweb.mongo_anime.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "movies")
public class Movie {

    @Id
    private String id;

    @NotBlank(message = "Title is required")
    @Size(min = 2, max = 100, message = "Title must be 2-100 characters")
    @Field("Title")
    private String title;

    @Field("Year")
    private String year;

    @NotBlank(message = "Director is required")
    @Size(min = 2, max = 50, message = "Director must be 2-50 characters")
    @Field("Director")
    private String director;

    @Field("Thumbnail")
    private String thumbnail;

    @Field("Genre")
    private String genre;

    @NotBlank(message = "Overview is required")
    @Size(min = 2, max = 200, message = "Overview must be 2-200 characters")
    @Field("Overview")
    private String overview;

    @Field("Release_Date")
    private String releaseDate;
    @Field("Original_Language")
    private String originalLanguage;
    @Field("Comment")
    private Integer comment;
    @Field("VoteCount")
    private Integer vote_count;
    @Field("VoteAverage")
    private Double vote_average;

    @Field("Create_Date")
    private String createDate;

    @Field("Update_Date")
    private String updateDate;

    // constructor dùng cho test
//    public Movie(Double vote) {
//        this.vote_average = vote;
//    }
//
//    public boolean isHighlyRated() {
//        return this.vote_average >= 8.0;
//    }
}
