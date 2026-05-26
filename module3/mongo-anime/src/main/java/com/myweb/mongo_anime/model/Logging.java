package com.myweb.mongo_anime.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@Document(collection = "logging")
public class Logging {

    @Id
    private String id;

    @NotBlank(message = "Level is required")
    @Field("Level")
    private String level;

    @NotBlank(message = "Message is required")
    @Field("Message")
    private String message;

    @Field("Create_Date")
    private String createDate;
}
