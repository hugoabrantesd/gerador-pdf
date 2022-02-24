package br.com.netline.veiculospdf.relatorio.connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaPersistence {

    private final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("pdf-generator");

    private EntityManager entityManager;

    public EntityManager getEntityManager(){
        if(entityManager == null){
            entityManager = entityManagerFactory.createEntityManager();
        }
        return entityManager;
    }

}
