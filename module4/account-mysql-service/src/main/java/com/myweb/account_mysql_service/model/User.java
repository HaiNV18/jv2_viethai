package com.myweb.account_mysql_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(
        name = "user",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "user_id"),
                @UniqueConstraint(columnNames = "email"),
                @UniqueConstraint(columnNames = "username")
        }
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "User ID is required")
    @Size(min = 2, max = 100, message = "User ID must be 2-100 characters")
    @Column(name = "user_id", nullable = false, length = 100)
    private String userId;

    @NotBlank(message = "Fullname is required")
    @Size(min = 2, max = 100, message = "Fullname must be 2-100 characters")
    @Column(name = "fullname", nullable = false, length = 100)
    private String fullname;

    @NotBlank(message = "Email is required")
    @Size(min = 2, max = 100, message = "Email must be 2-100 characters")
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @NotBlank(message = "Username is required")
    @Size(min = 2, max = 10, message = "Username must be 2-10 characters")
    @Column(name = "username", nullable = false, length = 10)
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 2, max = 50, message = "Password must be 2-50 characters")
    @Column(name = "password", nullable = false, length = 255)
    private String password;
}
