package com.prz.systemkurier.service;

import com.prz.systemkurier.criteria.Criteria;
import com.prz.systemkurier.domain.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    List<User> getAllUsers() throws SQLException;

    User getUserById(Long id) throws SQLException;

    void saveUser(User user) throws SQLException;

    void blockUser(User user) throws SQLException;

    void activateUser(User user) throws SQLException;

    List<User> getAllUsersPaginated(Criteria criteria) throws SQLException;

    List<User> getUsersWithCriteriaPaginated(Criteria criteria);

    Integer countUsersWithCriteria(Criteria criteria);

    void updateUser(User user) throws SQLException;
}
