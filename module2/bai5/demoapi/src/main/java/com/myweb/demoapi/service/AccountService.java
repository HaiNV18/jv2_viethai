package com.myweb.demoapi.service;

import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class AccountService {
    public Boolean validEmailFormat(String emailAddress){
        // Regular Expression - RegEx
        // Search RegEx generator
        String emailPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return Pattern.compile(emailPattern)
                .matcher(emailAddress)
                .matches();
    }
}
