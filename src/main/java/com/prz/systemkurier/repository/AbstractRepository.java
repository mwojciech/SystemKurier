package com.prz.systemkurier.repository;

import com.prz.systemkurier.criteria.Criteria;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public interface AbstractRepository<T> {

    Session getCurrentSession();

    void save(T t) throws SQLException;

    List<T> getAll() throws SQLException;

    T getById(Long id) throws SQLException;

    void delete(T t) throws SQLException;

    void delete(Long id) throws SQLException;

    void update(T t) throws SQLException;

    int countAll() throws SQLException;

    List<T> getPaginated(Criteria criteria) throws SQLException;

    List<T> getWithCriteriaPaginated(Criteria criteria);

    Integer countWithCriteria(Criteria criteria);

}
