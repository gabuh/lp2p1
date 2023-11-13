package dao.impl;

import config.JpaFactoryConnection;
import dao.GenericDAO;
import jakarta.persistence.EntityManager;
import model.Campeonato;

public class CampeonatoDao extends GenericDAO<Campeonato, Integer> {

    private final EntityManager entityManager;


    public CampeonatoDao() {
        this.entityManager = JpaFactoryConnection.getFacoryManager().createEntityManager();
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    protected Class<Campeonato> getEntityClass() {
        return Campeonato.class;
    }

    protected String getEntityName() {
        return "Campeonato";
    }






}
