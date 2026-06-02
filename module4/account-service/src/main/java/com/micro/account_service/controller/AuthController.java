package com.micro.account_service.controller;

import com.micro.account_service.dto.AccountResponse;
import com.micro.account_service.model.User;
import com.micro.account_service.service.UserService;
import com.micro.account_service.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
    public Map<String, String> login() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("user_id", "123456");
        claims.put("email", "leo.messi@fifa.com");

        String token = JwtUtil.generateToken(claims);

        Map<String, String> res = new HashMap<>();
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