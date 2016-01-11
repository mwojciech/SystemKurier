package com.prz.systemkurier.service.impl;

import com.prz.systemkurier.criteria.Criteria;
import com.prz.systemkurier.domain.Role;
import com.prz.systemkurier.domain.User;
import com.prz.systemkurier.enumerate.RoleName;
import com.prz.systemkurier.enumerate.Status;
import com.prz.systemkurier.repository.RoleRepository;
import com.prz.systemkurier.repository.UserRepository;
import com.prz.systemkurier.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public List<User> getAllUsers() throws SQLException {
        return userRepository.getAllUsers();
    }

    public User getUserById(Long id) throws SQLException {
        return userRepository.getById(id);
    }

    public void saveUser(User user) throws SQLException {
        user.setRole(roleRepository.getByName(RoleName.KURIER));
        System.out.println(user.getRole());
        user.setCreateDate(new Date());
        user.setStatus(Status.ACTIVE);
        userRepository.save(user);
    }

    public void saveRole() throws SQLException {
        Role role = new Role();
        role.setCreateDate(new Date());
        role.setName(RoleName.KURIER);
        roleRepository.save(role);
    }

    public void blockUser(User user) throws SQLException {
    }

    public void activateUser(User user) throws SQLException {

    }

    public List<User> getAllUsersPaginated(Criteria criteria) throws SQLException {
        return userRepository.getPaginated(criteria);
    }

    public List<User> getUsersWithCriteriaPaginated(Criteria criteria) {
        if (null == criteria.getOrderWay() || null == criteria.getOrderParam()) {
            criteria.setOrderWay("ASC");
            criteria.setOrderParam("lastName");
        }
        return userRepository.getWithCriteriaPaginated(criteria);
    }

    public Integer countUsersWithCriteria(Criteria criteria) {
        return userRepository.countWithCriteria(criteria);
    }

    public void updateUser(User user) throws SQLException {
        userRepository.update(user);
    }
}
