package com.myweb.ecommerce.service;

import com.myweb.ecommerce.model.Role;
import com.myweb.ecommerce.repository.RoleRepository;
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
