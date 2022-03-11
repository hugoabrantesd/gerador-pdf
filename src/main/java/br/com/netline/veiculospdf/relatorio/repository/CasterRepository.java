package br.com.netline.veiculospdf.relatorio.repository;

import br.com.netline.veiculospdf.relatorio.connection.JpaPersistence;
import br.com.netline.veiculospdf.relatorio.model.CasterModel;

import java.util.List;

public class CasterRepository {

    private final JpaPersistence jpaPersistence = new JpaPersistence();

    public void saveCaster(CasterModel caster) {
        jpaPersistence.getEntityManager().getTransaction().begin();

        jpaPersistence.getEntityManager().persist(caster);
        jpaPersistence.getEntityManager().getTransaction().commit();

        jpaPersistence.getEntityManager().close();
    }

    public List<CasterModel> getAllCasters(String vehiclePlate) {
        jpaPersistence.getEntityManager().getTransaction().begin();

        List<CasterModel> casters = jpaPersistence.getEntityManager()
                .createNamedQuery("getAll", CasterModel.class)
                .setParameter("placa", vehiclePlate)
                .getResultList();
        jpaPersistence.getEntityManager().close();

        //System.out.println(casters);
        return casters;
    }

}
