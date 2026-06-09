package com.myweb.account_mysql_service.service;

import com.myweb.account_mysql_service.model.User;
import com.myweb.account_mysql_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepo;

    public User findById(Long id) {
        return userRepo.findById(id).orElse(null);
    }

    public User findByUserId(String userId) {
        return userRepo.findByUserId(userId).orElse(null);
    }

    public User findByUsername(String username) {
        return userRepo.findByUsername(username).orElse(null);
    }

    public User findByEmail(String email) {
        return userRepo.findByEmail(email).orElse(null);
    }

    public User save(User user) {
        return userRepo.save(user);
    }
}
