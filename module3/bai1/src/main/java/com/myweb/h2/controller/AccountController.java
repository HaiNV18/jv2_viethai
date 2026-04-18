package com.myweb.h2.controller;

import com.myweb.h2.model.Account;
import com.myweb.h2.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    @Autowired
    private AccountRepository accRepo;

    @GetMapping("")
    public List<Account> getAllAccount() {
        return accRepo.findAll();
    }

    @GetMapping("/{accountId}")
    public List<Account> getAllAccountById(@PathVariable Integer accountId) {
        return accRepo.findById(accountId);
    }

    @GetMapping("/gender/{gender}")
    public List<Account> getAllAccountByGender(@PathVariable String gender) {
        return accRepo.findByGender(gender);
    }
}
