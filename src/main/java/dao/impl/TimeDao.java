package dao.impl;

import config.JpaFactoryConnection;
import dao.GenericDAO;
import jakarta.persistence.EntityManager;
import model.Time;

public class TimeDao extends GenericDAO<Time, Integer> {

    EntityManager entityManager;

    public TimeDao() {
        this.entityManager = JpaFactoryConnection.getFacoryManager().createEntityManager();
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    protected Class<Time> getEntityClass() {
        return Time.class;
    }
}
