package com.prz.systemkurier.repository;

import com.prz.systemkurier.domain.*;
import com.prz.systemkurier.domain.Package;

import java.sql.SQLException;
import java.util.List;


public interface PackageRepository extends AbstractRepository<Package>{

   // void test();

    List<Package> getAllPackages() throws SQLException;

    //Package getByCredentials(String lastName, String firstName) throws SQLException;

    List<Package> getByLocation(Location location) throws SQLException;
}
