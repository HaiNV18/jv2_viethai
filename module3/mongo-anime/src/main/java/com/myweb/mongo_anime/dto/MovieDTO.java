package com.myweb.mongo_anime.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MovieDTO {
    private String title;
    private String language;
    private Integer voteCount;
    private Double voteAverage;
    private String genre;
}
