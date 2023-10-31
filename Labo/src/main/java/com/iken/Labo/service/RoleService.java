package com.iken.Labo.service;

import com.iken.Labo.model.Role;
import com.iken.Labo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRoleById(String roleId) {
        return roleRepository.findById(roleId).orElse(null);
    }
    public Role findRoleByName(String roleName) {
        return roleRepository.findByRolename(roleName);
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }


}
