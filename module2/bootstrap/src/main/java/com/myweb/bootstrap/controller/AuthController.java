package com.myweb.bootstrap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "Đăng nhập");
        return "auth/login";
    }

    @GetMapping(value = "/register")
    public String showRegister() {
        return "auth/register";
    }

    @GetMapping("/forgot-password")
    public String showForgotPassword(Model model) {
        model.addAttribute("title", "Quên mật khẩu");
        return "auth/forgot-password";
    }
}
