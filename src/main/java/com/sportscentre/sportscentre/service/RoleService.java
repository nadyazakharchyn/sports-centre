package com.sportscentre.sportscentre.service;

import com.sportscentre.sportscentre.exception.RoleNotFoundException;
import com.sportscentre.sportscentre.model.Role;
import com.sportscentre.sportscentre.repository.RoleRepository;
import org.springframework.stereotype.Service;



@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRoleByCode(String code) {
        return roleRepository.findRoleByCode(code).orElseThrow(RoleNotFoundException::new);
    }
}
