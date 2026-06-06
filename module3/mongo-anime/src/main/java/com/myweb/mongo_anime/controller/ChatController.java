package com.myweb.mongo_anime.controller;

import com.myweb.mongo_anime.service.ChatService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/admin")
public class ChatController {

    @Value("${jwt.secret}")
    private String secretKey;

    @Autowired
    private ChatService chatService;

    @PostMapping("/api/public/chat")
    @ResponseBody
    public ResponseEntity<String> askAIChat(@RequestParam String question) {
        if (Objects.nonNull(question)){
            String output = chatService.sendRequest2Gemini(question);
            return new ResponseEntity<>(output, HttpStatus.OK);
        }
        return new ResponseEntity<>("Empty query", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/chat-bot")
    public String home(@CookieValue(value = "token", required = false) String token) {
        if (token == null || token.isBlank()) {
            return "redirect:/auth/login";
        }

        try {
            SecretKey key = Keys.hmacShaKeyFor(
                    secretKey.getBytes(StandardCharsets.UTF_8)
            );

            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            List<String> roles = claims.get("roles", List.class);

            if (roles == null || !roles.contains("ADMIN")) {
                return "redirect:/auth/login";
            }

            return "admin/chatbot";

        } catch (Exception e) {
            return "redirect:/auth/login";
        }
    }
}
