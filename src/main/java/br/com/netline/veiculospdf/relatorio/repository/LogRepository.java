package br.com.netline.veiculospdf.relatorio.repository;

import br.com.netline.veiculospdf.relatorio.connection.JpaPersistence;
import br.com.netline.veiculospdf.relatorio.model.LogModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class LogRepository {

    private final JpaPersistence jpaPersistence = new JpaPersistence();

    public void registerLog(LogModel log) {
        jpaPersistence.getEntityManager().getTransaction().begin();

        jpaPersistence.getEntityManager().persist(log);
        jpaPersistence.getEntityManager().getTransaction().commit();

        jpaPersistence.getEntityManager().close();
    }
}
