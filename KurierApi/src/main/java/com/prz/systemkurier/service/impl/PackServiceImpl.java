package com.prz.systemkurier.service.impl;

import com.prz.systemkurier.criteria.Criteria;
import com.prz.systemkurier.domain.Pack;
import com.prz.systemkurier.repository.PackRepository;
import com.prz.systemkurier.repository.RoleRepository;
import com.prz.systemkurier.service.PackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PackServiceImpl implements PackService {

    @Autowired
    private PackRepository packRepository;

    @Autowired
    private RoleRepository roleRepository;

    public List<Pack> getAllPacks() throws SQLException {
        return packRepository.getAllPacks();
    }

    public void savePack(Pack pack) throws SQLException {
        pack.setCreateDate(new Date());
        packRepository.save(pack);
    }

    public List<Pack> getAllPacksPaginated(Criteria criteria) throws SQLException {
        return packRepository.getPaginated(criteria);
    }

    public List<Pack> getPacksWithCriteriaPaginated(Criteria criteria) {
        if (null == criteria.getOrderWay() || null == criteria.getOrderParam()) {
            criteria.setOrderWay("ASC");
            criteria.setOrderParam("name");
        }
        return packRepository.getWithCriteriaPaginated(criteria);
    }

    public Integer countPacksWithCriteria(Criteria criteria) {
        return packRepository.countWithCriteria(criteria);
    }

    public void updatePack(Pack pack) throws SQLException {
        packRepository.update(pack);
    }
}
