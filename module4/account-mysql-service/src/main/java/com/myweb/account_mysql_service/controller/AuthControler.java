package com.myweb.account_mysql_service.controller;

import com.myweb.account_mysql_service.dto.LoginRequest;
import com.myweb.account_mysql_service.model.User;
import com.myweb.account_mysql_service.model.UserRole;
import com.myweb.account_mysql_service.service.UserService;
import com.myweb.account_mysql_service.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthControler {

    @Autowired
    public UserService userService;

    @PostMapping("/login")
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
//        List<UserRole> listUserRole = userRoleService.findByUserId(account.getUserId());
//        List<String> listRoles = listUserRole.stream()
//                .map(role -> {
//                    return roleService.getNameByRoleId(role.getRoleId());
//                })
//                .toList();

        List<String> listRoles = new ArrayList<>();
        listRoles.add("ADMIN");

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
}
