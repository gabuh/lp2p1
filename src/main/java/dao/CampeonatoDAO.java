package dao;

import jakarta.persistence.PersistenceContext;

import java.io.Serializable;
import java.util.List;

public interface CampeonatoDAO <T, Id extends Serializable>{

    @PersistenceContext
    void persist(T entity);

    @PersistenceContext
    void update(T entity);

    @PersistenceContext
    T findById(Id id);

    @PersistenceContext
    void delete(T entity);

    @PersistenceContext
    List<T> findAll();

}
