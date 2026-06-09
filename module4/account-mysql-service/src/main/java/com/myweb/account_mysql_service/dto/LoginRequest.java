package com.myweb.account_mysql_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String username;
    private String email;
    private String password;
}
