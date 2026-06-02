package com.micro.account_service.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "user")
public class UserRole {

    @Id
    private String id;

    @Field("user_id")
    private String userId;

    @Field("role_id")
    private String roleId;
}
