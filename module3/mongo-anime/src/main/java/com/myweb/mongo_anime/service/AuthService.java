package com.myweb.mongo_anime.service;

import com.myweb.mongo_anime.dto.LoginResponse;
import com.myweb.mongo_anime.request.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final RestTemplate restTemplate;

    public LoginResponse login(LoginRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<LoginRequest> entity = new HttpEntity<>(request, headers);

        ResponseEntity<LoginResponse> response =
                restTemplate.postForEntity(
                        "http://localhost:8081/api/v1/auth/login",
                        entity,
                        LoginResponse.class
                );

        return response.getBody();
    }
}
