package com.prz.systemkurier.service;

import com.prz.systemkurier.criteria.Criteria;
import com.prz.systemkurier.domain.Package;

import java.sql.SQLException;
import java.util.List;

public interface PackageService {

    List<Package> getAllPackages() throws SQLException;

    Package getPackageById(Long id) throws SQLException;

    void savePackage(Package pack) throws SQLException;

    void deletePackage(Package pack) throws SQLException;

    //void activateUser(Package pack) throws SQLException;

    //List<Package> getAllUsersPaginated(Criteria criteria) throws SQLException;
    //List<Package> getUsersWithCriteriaPaginated(Criteria criteria);

    Integer countPackageWithCriteria(Criteria criteria);
}
