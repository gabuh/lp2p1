package dao.impl;

import config.JpaFactoryConnection;
import dao.GenericDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
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


    public boolean checkIfUsernameExist(String username){
        TypedQuery<Long> query = getEntityManager().createQuery(
                "SELECT COUNT(u) FROM " + getEntityClass().getSimpleName() + " u WHERE u.username = :username",
                Long.class
        );
        query.setParameter("username", username);
        Long count = query.getSingleResult();
        return count > 0;
    }


    public boolean checkUsuario(String username, String password){
        TypedQuery<Long> query = getEntityManager().createQuery(
                "SELECT COUNT(u) FROM " + getEntityClass().getSimpleName() + " u WHERE u.username = :username AND u.senha = :password",
                Long.class
        );
        query.setParameter("username", username);
        query.setParameter("password", password);
        Long count = query.getSingleResult();
        return count > 0;
    }


    public Tecnico getUserByUsernameAndPassword(String username, String password){
        TypedQuery<Tecnico> query = getEntityManager().createQuery(
                "SELECT u FROM " + getEntityClass().getSimpleName() + " u WHERE u.username = :username AND u.senha = :password",
                Tecnico.class
        );
        query.setParameter("username", username);
        query.setParameter("password", password);
        return query.getSingleResult();
    }



}
