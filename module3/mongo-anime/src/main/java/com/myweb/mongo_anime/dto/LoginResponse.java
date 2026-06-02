package com.myweb.mongo_anime.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LoginResponse {
    private List<String> roles;
    private String message;
    private String status;
    private String username;
    private String token;
}
