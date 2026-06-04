package com.micro.account_service.service;

import com.micro.account_service.model.UserRole;
import com.micro.account_service.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepo;

    public List<UserRole> findByUserId(String userId) {
        return userRoleRepo.findByUserId(userId);
    }
}
