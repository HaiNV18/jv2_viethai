package com.myweb.h2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ACCOUNTS")
public class Account {

    @Id
    private Long id;

    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String gender;
    private String email;
    private String phone;
    private Integer role;
    private Integer id_country;
}