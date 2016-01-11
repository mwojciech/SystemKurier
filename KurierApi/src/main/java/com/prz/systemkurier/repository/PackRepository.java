package com.prz.systemkurier.repository;

import com.prz.systemkurier.domain.Pack;

import java.sql.SQLException;
import java.util.List;


public interface PackRepository extends AbstractRepository<Pack>{

    void test();

    List<Pack> getAllPacks() throws SQLException;
}
