package com.prz.systemkurier.repository;

import com.prz.systemkurier.domain.User;
import com.prz.systemkurier.enumerate.RoleName;

import java.sql.SQLException;
import java.util.List;



public interface UserRepository extends AbstractRepository<User>{

    void test();

    List<User> getAllUsers() throws SQLException;

    User getByCredentials(String lastName, String firstName) throws SQLException;

    List<User> getByRoleName(RoleName roleNames) throws SQLException;
}
