package com.prz.systemkurier.service;

import com.prz.systemkurier.criteria.Criteria;
import com.prz.systemkurier.domain.Pack;


import java.sql.SQLException;
import java.util.List;

public interface PackService {

    List<Pack> getAllPacks() throws SQLException;

    void savePack(Pack pack) throws SQLException;

    List<Pack> getAllPacksPaginated(Criteria criteria) throws SQLException;

    List<Pack> getPacksWithCriteriaPaginated(Criteria criteria);

    Integer countPacksWithCriteria(Criteria criteria);

    void updatePack(Pack pack) throws SQLException;
}
