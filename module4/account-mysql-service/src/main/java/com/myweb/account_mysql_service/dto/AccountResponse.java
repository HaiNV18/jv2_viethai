package com.myweb.account_mysql_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {
    private Long id;
    private String fullname;
    private String username;
    private String email;
    private String token; // xac thuc
    private Integer role; // phan quyen
}
