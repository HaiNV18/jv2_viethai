package com.micro.account_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {
    private Integer id;
    private String name;
    private String token; // xac thuc
    private Integer role; // phan quyen
}
