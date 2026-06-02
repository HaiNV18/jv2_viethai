package com.micro.account_service.controller;

import com.micro.account_service.dto.AccountResponse;
import com.micro.account_service.dto.LoginRequest;
import com.micro.account_service.model.User;
import com.micro.account_service.service.UserService;
import com.micro.account_service.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

    @Autowired
    public UserService userService;

    @GetMapping("/account/hello-world")
    public String helloWorld() {
        return "Hello World account-service";
    }

    @GetMapping("/account/detail/{id}")
    public AccountResponse getAccountDetail(@PathVariable String id) {
        User account = userService.findById(id);

        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setId(account.getId());
        accountResponse.setFullname(account.getFullname());
        accountResponse.setUsername(account.getFullname());
        accountResponse.setEmail(account.getEmail());

        return accountResponse;
    }

    @PostMapping("/auth/login")
    @ResponseBody
    public Map<String, Object> login(@RequestBody LoginRequest request) {

        // Step 1: findByUsername
        // Step 2: check password
        // Step 3: check role List<String> roles = findByUserId
        // Map có status, message

        Map<String, Object> claims = new HashMap<>();
        claims.put("user_id", "123456");
        claims.put("roles", "[ADMIN]");

        String token = JwtUtil.generateToken(claims);

        Map<String, Object> res = new HashMap<>();
        res.put("status", "200");
        res.put("message", "Success");
        res.put("username", request.getUsername());
        res.put("roles", "[ADMIN]");
        res.put("token", token);
        return res;
    }

    @GetMapping("/booking_info")
    public ResponseEntity<String> getBookingInfo(HttpServletRequest request) {
        Claims claims = (Claims) request.getAttribute("claims");
        String userId = claims.get("user_id", String.class);
        return new ResponseEntity<>("Booking for user ID: " + userId, HttpStatus.OK);
    }
}