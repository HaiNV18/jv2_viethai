package com.myweb.ecommerce.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "role")
public class Role {

    @Id
    private String id;

    @Field("role_id")
    private String roleId;

    @Field("role_name")
    private String roleName;
}
