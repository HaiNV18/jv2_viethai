package com.myweb.ecommerce.service;

import com.myweb.ecommerce.dto.request.LoginRequest;
import com.myweb.ecommerce.dto.response.LoginResponse;
import com.myweb.ecommerce.model.User;
import com.myweb.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class UserService {

    private final RestTemplate restTemplate;

    @Autowired
    public UserRepository userRepo;

    public User findById(String id) {
        return userRepo.findById(id).orElse(null);
    }

    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public User save(User user) {
        return userRepo.save(user);
    }

    public LoginResponse login(LoginRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<LoginRequest> entity = new HttpEntity<>(request, headers);

        ResponseEntity<LoginResponse> response =
                restTemplate.postForEntity(
                        "http://localhost:8080/api/v1/auth/login",
                        entity,
                        LoginResponse.class
                );

        return response.getBody();
    }
}
