package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.io.Serializable;
import java.util.List;

public abstract class GenericDAO<T, Id extends Serializable>{

    protected abstract EntityManager getEntityManager();
    protected abstract Class<T> getEntityClass();


    public void persist(T entity) {
        EntityTransaction transaction = getEntityManager().getTransaction();
        try {
            transaction.begin();
            getEntityManager().persist(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    public void update(T entity) {
        EntityTransaction transaction = getEntityManager().getTransaction();
        try {
            transaction.begin();
            getEntityManager().merge(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    public T findById(Id id) {
        return getEntityManager().find(getEntityClass(), id);
    }

    public void delete(T entity) {
        EntityTransaction transaction = getEntityManager().getTransaction();
        try {
            transaction.begin();
            getEntityManager().remove(getEntityManager().contains(entity) ? entity : getEntityManager().merge(entity));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    public List<T> findAll() {
        return getEntityManager().createQuery("SELECT t FROM "+ getEntityClass().getSimpleName()+ " t", getEntityClass()).getResultList();
    }


    public void close() {
        if (getEntityManager() != null) {
            getEntityManager().close();
        }
    }

}
