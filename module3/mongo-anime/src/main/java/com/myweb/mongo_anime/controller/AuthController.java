package com.myweb.mongo_anime.controller;

import com.myweb.mongo_anime.dto.LoginResponse;
import com.myweb.mongo_anime.model.Movie;
import com.myweb.mongo_anime.request.LoginRequest;
import com.myweb.mongo_anime.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping(value = "/login", produces = MediaType.TEXT_HTML_VALUE)
    public String showDashboard(Model model) {
        return "admin/signin";
    }

    @PostMapping("/submit-login")
    public String submitLogin(LoginRequest req) {
        System.out.println(req.getUsername());

        // Call API localhost:8081/api/v1/auth/login
        LoginResponse loginResponse = authService.login(req);

        if (loginResponse != null && loginResponse.getToken() != null) {
            // Check token

            // Redirect dashboard
            return "redirect:/admin/dashboard";
        }

        // Redirect Login
        return "redirect:/auth/login";
    }
}
