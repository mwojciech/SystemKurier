package com.prz.systemkurier.repository.impl;


import com.prz.systemkurier.domain.*;
import com.prz.systemkurier.domain.Package;
import com.prz.systemkurier.repository.PackageRepository;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Repository
@Transactional
public class PackageRepositoryImpl extends AbstractRepositoryImpl<Package> implements PackageRepository {

    PackageRepositoryImpl() {
        super(Package.class);
    }

    /*public void test() {
        getCurrentSession();
    }*/

    public List<Package> getAllPackages() throws SQLException {
        List<Package> packages = new ArrayList<Package>();
        packages = getCurrentSession().createCriteria(Package.class).list();
        return packages;
    }

  /*  public User getByCredentials(String lastName, String firstName) throws SQLException {
        User user = new User();
        user = (User) getCurrentSession().createCriteria(User.class)
                .add(Restrictions.eq("lastName", lastName))
                .add(Restrictions.eq("firstName", firstName)).uniqueResult();
        return user;
    }*/

    public List<Package> getByLocation(Location location) throws SQLException {
        List<Package> packages = new ArrayList<Package>();
        packages = getCurrentSession().createCriteria(Package.class).add(Restrictions.eq("id", location)).list();
        return packages;
    }
}