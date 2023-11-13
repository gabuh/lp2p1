package dao.impl;

import config.JpaFactoryConnection;
import dao.GenericDAO;
import jakarta.persistence.EntityManager;
import model.Jogador;

public class JogadorDao extends GenericDAO<Jogador, Integer> {

    private final EntityManager entityManager;

    public JogadorDao() {
        this.entityManager = JpaFactoryConnection.getFacoryManager().createEntityManager();
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    protected Class<Jogador> getEntityClass() {
        return Jogador.class;
    }


}
