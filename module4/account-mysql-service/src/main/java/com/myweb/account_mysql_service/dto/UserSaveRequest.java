package com.myweb.account_mysql_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSaveRequest {
    private Long id;
    private String userId;
    private String fullname;
    private String email;
    private String username;
    private String password;
}
