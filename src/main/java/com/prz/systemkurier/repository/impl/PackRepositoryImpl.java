package com.prz.systemkurier.repository.impl;

import com.prz.systemkurier.domain.Pack;
import com.prz.systemkurier.repository.PackRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class PackRepositoryImpl extends AbstractRepositoryImpl<Pack> implements PackRepository {

    PackRepositoryImpl() {
        super(Pack.class);
    }

    public void test() {
        getCurrentSession();
    }

    public List<Pack> getAllPacks() throws SQLException {
        List<Pack> packs = new ArrayList<Pack>();
        packs = getCurrentSession().createCriteria(Pack.class).list();
        return packs;
    }
}
