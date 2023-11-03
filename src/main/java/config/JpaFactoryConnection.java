package config;

import jakarta.persistence.*;

public class JpaFactoryConnection {
    @PersistenceContext
    private static EntityManager entityManager;

    public static void connect(){
        try{
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
            entityManager = entityManagerFactory.createEntityManager();
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static EntityManager getEntityManager() {
        if(entityManager == null){
            connect();
            return entityManager;
        }
        return entityManager;
    }


    public static void closeConnection(){
        entityManager.close();
    }


}
