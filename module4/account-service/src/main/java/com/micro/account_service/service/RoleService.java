package com.micro.account_service.service;

import com.micro.account_service.model.Role;
import com.micro.account_service.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    public RoleRepository roleRepo;

    public String getNameByRoleId(String roleId) {
        Role role = roleRepo.findByRoleId(roleId);
        return role.getRoleName();
    }
}
