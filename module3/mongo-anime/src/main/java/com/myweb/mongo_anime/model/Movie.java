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
    @Field("Thumbnail")
    private String thumbnail;
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

    // constructor dùng cho test
    public Movie(Double vote) {
        this.vote_average = vote;
    }

    public boolean isHighlyRated() {
        return this.vote_average >= 8.0;
    }
}
