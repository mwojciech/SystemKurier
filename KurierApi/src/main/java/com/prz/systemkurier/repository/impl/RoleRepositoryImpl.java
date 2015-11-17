package com.prz.systemkurier.repository.impl;

import com.prz.systemkurier.domain.Role;
import com.prz.systemkurier.enumerate.RoleName;
import com.prz.systemkurier.repository.RoleRepository;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;


@Repository
@Transactional
public class RoleRepositoryImpl extends AbstractRepositoryImpl<Role> implements RoleRepository {

    RoleRepositoryImpl() {
        super(Role.class);
    }

    public Role getByName(RoleName name) {
        Role role = new Role();
        Criteria crit = getCurrentSession().createCriteria(Role.class);

        role = (Role) crit.add(Restrictions.eq("name", name)).uniqueResult();
        return role;
    }

    public void saveRole(Role role) throws SQLException {
        getCurrentSession().save(role);
    }

}
