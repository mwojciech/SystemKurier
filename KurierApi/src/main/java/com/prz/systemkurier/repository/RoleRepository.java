package com.prz.systemkurier.repository;

import com.prz.systemkurier.domain.Role;
import com.prz.systemkurier.enumerate.RoleName;

import java.sql.SQLException;


public interface RoleRepository extends AbstractRepository<Role>{
    Role getByName(RoleName name);


    void saveRole(Role role) throws SQLException;
}
