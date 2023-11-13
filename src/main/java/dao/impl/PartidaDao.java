package dao.impl;

import config.JpaFactoryConnection;
import dao.GenericDAO;
import jakarta.persistence.EntityManager;
import model.Partida;

public class PartidaDao extends GenericDAO<Partida, Integer> {

    EntityManager entityManager;

    public PartidaDao() {
        this.entityManager = JpaFactoryConnection.getFacoryManager().createEntityManager();
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    protected Class<Partida> getEntityClass() {
        return Partida.class;
    }
}
