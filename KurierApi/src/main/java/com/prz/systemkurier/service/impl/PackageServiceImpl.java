package com.prz.systemkurier.service.impl;

import com.prz.systemkurier.criteria.Criteria;
import com.prz.systemkurier.domain.Package;

import com.prz.systemkurier.repository.PackageRepository;
import com.prz.systemkurier.repository.RoleRepository;
import com.prz.systemkurier.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;


@Service
@Transactional
public class PackageServiceImpl implements PackageService {

    @Autowired
    private PackageRepository packageRepository;

    @Autowired
    private RoleRepository roleRepository;

    public List<Package> getAllPackages() throws SQLException {
        return packageRepository.getAllPackages();
    }

    public Package getPackageById(Long id) throws SQLException {
        return packageRepository.getById(id);
    }

    public void savePackage(Package pack) throws SQLException {
       // pack.setRole(roleRepository.getByName(RoleName.KURIER));
        packageRepository.save(pack);
    }

   /* public void saveRole() throws SQLException{
        Role role = new Role();
        role.setCreateDate(new Date());
        role.setName(RoleName.KURIER);
        roleRepository.save(role);
    }*/

    public void deletePackage(Package pack) throws SQLException {
    }

    /*public void activateUser(User user) throws SQLException {

    }*/

    /*public List<User> getAllUsersPaginated(Criteria criteria) throws SQLException {
        return packageRepository.getPaginated(criteria);
    }*/

    /*public List<User> getUsersWithCriteriaPaginated(Criteria criteria) {
        if(null == criteria.getOrderWay() || null == criteria.getOrderParam()){
            criteria.setOrderWay("ASC");
            criteria.setOrderParam("lastName");
        }
        return packageRepository.getWithCriteriaPaginated(criteria);
    }*/

    public Integer countPackageWithCriteria(Criteria criteria) {
        return packageRepository.countWithCriteria(criteria);
    }
}
