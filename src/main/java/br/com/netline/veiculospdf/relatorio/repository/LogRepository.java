package br.com.netline.veiculospdf.relatorio.repository;

import br.com.netline.veiculospdf.relatorio.jpa_connection.JpaPersistence;
import br.com.netline.veiculospdf.relatorio.model.LogModel;

public class LogRepository {

    private final JpaPersistence jpaPersistence = new JpaPersistence();

    public void registerLog(LogModel log) {
        jpaPersistence.getEntityManager().getTransaction().begin();

        jpaPersistence.getEntityManager().persist(log);
        jpaPersistence.getEntityManager().getTransaction().commit();

        jpaPersistence.getEntityManager().close();
    }

    public void getAllCasters(){
        jpaPersistence.getEntityManager().getTransaction().begin();


    }

}



