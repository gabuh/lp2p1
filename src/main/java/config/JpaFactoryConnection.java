package config;

import jakarta.persistence.*;

public class JpaFactoryConnection {
    @PersistenceContext
    private static EntityManagerFactory entityManagerFactory;

    public static void connect(){
        try{
             entityManagerFactory = Persistence.createEntityManagerFactory("default");
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static EntityManagerFactory getFacoryManager() {
        if(entityManagerFactory == null){
            connect();
            return entityManagerFactory;
        }
        return entityManagerFactory;
    }


    public static void closeConnection(){
        if (entityManagerFactory != null){
            entityManagerFactory.close();
        }
    }


}
