package dao.impl;

import config.JpaFactoryConnection;
import dao.GenericDAO;
import jakarta.persistence.EntityManager;
import model.Tecnico;

public class TecnicoDao extends GenericDAO<Tecnico, Integer> {

    EntityManager entityManager;

    public TecnicoDao() {
        this.entityManager = JpaFactoryConnection.getFacoryManager().createEntityManager();
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    protected Class<Tecnico> getEntityClass() {
        return Tecnico.class;
    }
}
