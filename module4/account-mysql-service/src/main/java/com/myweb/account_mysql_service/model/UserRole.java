package com.myweb.account_mysql_service.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user_role")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "role_id", nullable = false)
    private String roleId;
}
