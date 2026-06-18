package com.myweb.ecommerce.service;

import com.myweb.ecommerce.model.UserRole;
import com.myweb.ecommerce.repository.UserRoleRepository;
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
