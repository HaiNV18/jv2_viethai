package com.micro.account_service.controller;

import com.micro.account_service.dto.AccountResponse;
import com.micro.account_service.dto.LoginRequest;
import com.micro.account_service.model.JwtBlacklist;
import com.micro.account_service.model.User;
import com.micro.account_service.model.UserRole;
import com.micro.account_service.repository.JwtBlacklistRepository;
import com.micro.account_service.service.EmailService;
import com.micro.account_service.service.RoleService;
import com.micro.account_service.service.UserRoleService;
import com.micro.account_service.service.UserService;
import com.micro.account_service.util.JwtUtil;
import com.micro.account_service.util.PassUtil;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

    @Autowired
    public EmailService emailService;

    @Autowired
    public UserService userService;

    @Autowired
    public UserRoleService userRoleService;

    @Autowired
    public RoleService roleService;

    @Autowired
    public JwtBlacklistRepository jwtBlacklistRepository;

    @GetMapping("/account/hello-world")
    public String helloWorld() {
        return "Hello World deploy render.com";
    }

    @GetMapping("/account/detail/{username}")
    public AccountResponse getAccountDetail(@PathVariable String username) {
        User account = userService.findByUsername(username);

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
        User account = userService.findByUsername(request.getUsername());
        if (account == null) {
            Map<String, Object> response = new HashMap<>();
            response.put("status", HttpStatus.UNAUTHORIZED.value());
            response.put("message", "Invalid username or password");
            return response;
        }

        // Step 2: check password
        if (!request.getPassword().equals(account.getPassword())) {
            Map<String, Object> response = new HashMap<>();
            response.put("status", HttpStatus.UNAUTHORIZED.value());
            response.put("message", "Invalid username or password");
            return response;
        }

        // Step 3: check role List<String> roles = findByUserId
        List<UserRole> listUserRole = userRoleService.findByUserId(account.getUserId());
        List<String> listRoles = listUserRole.stream()
                                .map(role -> {
                                    return roleService.getNameByRoleId(role.getRoleId());
                                })
                                .toList();

        Map<String, Object> claims = new HashMap<>();
        claims.put("user_id", "123456");
        claims.put("roles", listRoles);

        String token = JwtUtil.generateToken(claims);

        Map<String, Object> res = new HashMap<>();
        res.put("status", "200");
        res.put("message", "Success");
        res.put("username", request.getUsername());
        res.put("roles", listRoles);
        res.put("token", token);
        return res;
    }

    @PostMapping("/auth/forgot-password")
    @ResponseBody
    public Map<String, Object> forgotPassword(@RequestBody LoginRequest request) {

        // check email exist
        String emailTo = request.getEmail();
        User account = userService.findByEmail(emailTo);
        if (account == null) {
            Map<String, Object> response = new HashMap<>();
            response.put("status", HttpStatus.UNAUTHORIZED.value());
            response.put("message", "Email not found");
            return response;
        }

        // Generate new password
        String newPassword = PassUtil.generatePassword();

        // Update new password for user
        account.setPassword(newPassword);
        userService.save(account);

        // Send email
        emailService.sendEmail(
                emailTo,
                "Test Email",
                "New password: " + newPassword
        );

        Map<String, Object> res = new HashMap<>();
        res.put("status", "200");
        res.put("message", "Success");
        res.put("newPassword", newPassword);
        return res;
    }

    @PostMapping("/auth/logout")
    @ResponseBody
    public Map<String, Object> logout(HttpServletRequest request) {

        Map<String, Object> response = new HashMap<>();

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("message", "Token is missing");
            return response;
        }

        String token = authHeader.substring(7);

        // Nếu token đã nằm trong blacklist thì không cần lưu lại
        if (!jwtBlacklistRepository.existsByToken(token)) {

            JwtBlacklist blacklist = new JwtBlacklist();
            blacklist.setToken(token);
            blacklist.setExpiredAt(JwtUtil.getExpiration(token));

            jwtBlacklistRepository.save(blacklist);
        }

        response.put("status", HttpStatus.OK.value());
        response.put("message", "Logout success");

        return response;
    }


    @GetMapping("/booking_info")
    public ResponseEntity<String> getBookingInfo(HttpServletRequest request) {
        Claims claims = (Claims) request.getAttribute("claims");
        String userId = claims.get("user_id", String.class);
        return new ResponseEntity<>("Booking for user ID: " + userId, HttpStatus.OK);
    }
}