package br.com.netline.veiculospdf.relatorio.interfaces;

import br.com.netline.veiculospdf.relatorio.model.FlowModel;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

import java.util.List;

public interface ReportFlows {

    void gerarCabecalho(Document doc, String emailUsuario) throws DocumentException;

    int gerarCorpo(List<FlowModel> fluxos, Document doc) throws DocumentException;

    void gerarRodape(Document doc, List<FlowModel> fluxos, int totKm, String periodo) throws DocumentException;

    byte[] gerarRelatorio(List<FlowModel> fluxos, String periodo, String emailUsuario);

}
