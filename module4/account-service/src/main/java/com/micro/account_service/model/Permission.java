package com.micro.account_service.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "permission")
public class Permission {

    @Id
    private String id;

    @Field("permission_id")
    private String permissionId;

    @Field("action")
    private String action;
}
