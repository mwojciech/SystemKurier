package com.prz.systemkurier.repository.impl;

import com.prz.systemkurier.domain.User;
import com.prz.systemkurier.enumerate.RoleName;
import com.prz.systemkurier.repository.UserRepository;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class UserRepositoryImpl extends AbstractRepositoryImpl<User> implements UserRepository {

    UserRepositoryImpl() {
        super(User.class);
    }

    public void test() {
        getCurrentSession();
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<User>();
        users = getCurrentSession().createCriteria(User.class).list();
        return users;
    }

    public User getByCredentials(String lastName, String firstName) throws SQLException {
        User user = new User();
        user = (User) getCurrentSession().createCriteria(User.class)
                .add(Restrictions.eq("lastName", lastName))
                .add(Restrictions.eq("firstName", firstName)).uniqueResult();
        return user;
    }

    public List<User> getByRoleName(RoleName roleName) throws SQLException {
        List<User> users = new ArrayList<User>();
        users = getCurrentSession().createCriteria(User.class).add(Restrictions.eq("roleName", roleName)).list();
        return users;
    }
}
