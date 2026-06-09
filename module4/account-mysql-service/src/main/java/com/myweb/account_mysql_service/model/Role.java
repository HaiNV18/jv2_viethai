package com.myweb.account_mysql_service.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_id")
    private String roleId;

    @Column(name = "role_name")
    private String roleName;
}
