package dao.impl;

import config.JpaFactoryConnection;
import dao.CampeonatoDAO;
import model.Campeonato;

import java.util.List;

public class CampeonatoDaoImpl implements CampeonatoDAO<Campeonato, Integer> {


    @Override
    public void persist(Campeonato entity) {
        JpaFactoryConnection.getEntityManager().persist(entity);
    }

    @Override
    public void update(Campeonato entity) {
        JpaFactoryConnection.getEntityManager().merge(entity);
    }

    @Override
    public Campeonato findById(Integer integer) {
        return JpaFactoryConnection.getEntityManager().find(Campeonato.class, integer);
    }

    @Override
    public void delete(Campeonato entity) {
        JpaFactoryConnection.getEntityManager().remove(JpaFactoryConnection.getEntityManager().contains(entity)? entity : JpaFactoryConnection.getEntityManager().merge(entity));
    }

    @Override
    public List<Campeonato> findAll() {
        return JpaFactoryConnection.getEntityManager().createQuery("SELECT c FROM Campeonato AS c",Campeonato.class).getResultList();
    }

}
