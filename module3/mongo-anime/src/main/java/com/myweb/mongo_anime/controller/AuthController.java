package com.myweb.mongo_anime.controller;

import com.myweb.mongo_anime.dto.LoginResponse;
import com.myweb.mongo_anime.request.LoginRequest;
import com.myweb.mongo_anime.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
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
    public String submitLogin(LoginRequest req,
                              HttpServletResponse response) {

        LoginResponse loginResponse = authService.login(req);

        if (loginResponse != null && loginResponse.getToken() != null) {

            Cookie cookie = new Cookie("token", loginResponse.getToken());

            cookie.setHttpOnly(true);      // JS không đọc được
            cookie.setSecure(false);       // true nếu chạy HTTPS
            cookie.setPath("/");           // dùng cho toàn bộ website
            cookie.setMaxAge(24 * 60 * 60); // 1 ngày

            response.addCookie(cookie);

            return "redirect:/admin/dashboard";
        }
        return "redirect:/auth/login";
    }
}
