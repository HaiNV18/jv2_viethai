package com.myweb.ecommerce.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String id;
    private String fullname;
    private String username;
    private String email;
    private String token; // xac thuc
    private Integer role; // phan quyen
}
