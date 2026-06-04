package com.micro.account_service.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "resource")
public class Resource {

    @Id
    private String id;

    @Field("resource_id")
    private String resourceId;

    @Field("resource_name")
    private String resourceName;
}
