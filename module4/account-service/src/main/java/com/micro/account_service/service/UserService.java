package com.micro.account_service.service;

import com.micro.account_service.model.User;
import com.micro.account_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepo;

    public User findById(String id) {
        return userRepo.findById(id).orElse(null);
    }
}
