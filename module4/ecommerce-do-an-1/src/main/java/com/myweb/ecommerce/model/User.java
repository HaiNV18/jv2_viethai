package com.myweb.ecommerce.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "user")
public class User {

    @Id
    private String id;

    @NotBlank(message = "User ID is required")
    @Size(min = 2, max = 100, message = "User ID must be 2-20 characters")
    @Field("user_id")
    private String userId;

    @NotBlank(message = "Fullname is required")
    @Size(min = 2, max = 100, message = "Fullname must be 2-100 characters")
    @Field("fullname")
    private String fullname;

    @NotBlank(message = "Email is required")
    @Size(min = 2, max = 100, message = "Email must be 2-100 characters")
    @Field("email")
    private String email;

    @NotBlank(message = "Username is required")
    @Size(min = 2, max = 10, message = "Username must be 2-10 characters")
    @Field("username")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 2, max = 20, message = "Password must be 2-20 characters")
    @Field("password")
    private String password;

}
