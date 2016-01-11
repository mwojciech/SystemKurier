package com.prz.systemkurier.service.impl;

import com.prz.systemkurier.domain.Role;
import com.prz.systemkurier.enumerate.RoleName;
import com.prz.systemkurier.repository.RoleRepository;
import com.prz.systemkurier.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public void saveRole(Role role) throws SQLException{
        roleRepository.save(role);
    }

    public Role getRole(RoleName roleName) throws SQLException{
        return roleRepository.getByName(RoleName.KURIER);
    }
}
