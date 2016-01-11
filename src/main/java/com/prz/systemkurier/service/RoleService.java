package com.prz.systemkurier.service;

import com.prz.systemkurier.domain.Role;
import com.prz.systemkurier.enumerate.RoleName;

import java.sql.SQLException;

public interface RoleService {
    
    void saveRole(Role role) throws SQLException;

    Role getRole(RoleName roleName) throws SQLException;
}
