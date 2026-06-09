package com.myweb.account_mysql_service.controller;

import com.myweb.account_mysql_service.dto.AccountResponse;
import com.myweb.account_mysql_service.dto.LoginRequest;
import com.myweb.account_mysql_service.dto.UserSaveRequest;
import com.myweb.account_mysql_service.model.User;
import com.myweb.account_mysql_service.service.UserService;
import com.myweb.account_mysql_service.util.PassUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    public UserService userService;

    @GetMapping("/hello-world")
    public String helloWorld(){
        return "hello world";
    }

    @GetMapping("/detail/{id}")
    public AccountResponse getAccountDetail(@PathVariable String id) {
        User account = userService.findByUserId(id);

        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setId(account.getId());
        accountResponse.setFullname(account.getFullname());
        accountResponse.setUsername(account.getFullname());
        accountResponse.setEmail(account.getEmail());

        return accountResponse;
    }

    @PostMapping("/save")
    public User getAccountDetail(@RequestBody UserSaveRequest request) {
        User req = new User();
        req.setId(request.getId());
        req.setUserId(request.getUserId());
        req.setFullname(request.getFullname());
        req.setEmail(request.getEmail());
        req.setUsername(request.getUsername());
        req.setPassword(PassUtil.md5(request.getPassword()));

        userService.save(req);

        return req;
    }


}
