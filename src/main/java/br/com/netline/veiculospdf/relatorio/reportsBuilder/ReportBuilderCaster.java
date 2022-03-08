package br.com.netline.veiculospdf.relatorio.reportsBuilder;

import br.com.netline.veiculospdf.relatorio.model.CasterModel;
import br.com.netline.veiculospdf.relatorio.repository.CasterRepository;

import java.util.List;

public class ReportBuilderCaster {

    private final CasterRepository casterRepository = new CasterRepository();

    public void buildReport(String plate){
        // TODO: gerar o relatório a partir dessa lista
        List<CasterModel> casters = casterRepository.getAllCasters(plate);
    }
}
