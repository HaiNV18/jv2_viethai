package com.myweb.mongo_anime.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "comments")
public class Comment {
    private String name;
    private String email;
    private String message;
}
