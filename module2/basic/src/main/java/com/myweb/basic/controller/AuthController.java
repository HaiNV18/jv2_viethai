package com.myweb.basic.controller;

import com.myweb.basic.dto.LoginRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "Đăng nhập");
        model.addAttribute("loginRequest", new LoginRequest());
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

    @PostMapping("/submit-forgot-password")
    public String submitForgotPassword(
            @RequestParam String email,
            RedirectAttributes redirectAttributes
    ) {

        if (email == null || email.isBlank()) {
            redirectAttributes.addFlashAttribute("error",
                    "Email không được để trống");

            return "redirect:/auth/forgot-password";
        }

        // TODO: Kiểm tra email có tồn tại không
        // TODO: Gửi email

        redirectAttributes.addFlashAttribute("success",
                "Đã gửi email khôi phục mật khẩu.");

        return "redirect:/auth/login";
    }

    @PostMapping("/submit-login")
    public String submitLogin(
            @Valid @ModelAttribute("loginRequest") LoginRequest req,
            BindingResult result
    ) {

        if (result.hasErrors()) {
            return "auth/login";
        }

        if ("admin@gmail.com".equals(req.getEmail())
                && "123456".equals(req.getPassword())) {
            return "redirect:/admin/dashboard";
        }

        result.reject("loginError", "Email hoặc Password không đúng");

        return "auth/login";
    }
}
