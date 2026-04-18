package com.myweb.mongo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "bai3")
public class Notification {

    @Id
    private String id;

    private String accountId;
    private String title;
    private String message;
    private String type;
    private boolean isRead;
    private String createdAt;
}
