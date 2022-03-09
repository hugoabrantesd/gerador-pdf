package br.com.netline.veiculospdf.relatorio.interfaces;

import br.com.netline.veiculospdf.relatorio.model.FlowModel;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

import java.util.List;

public interface ReportFlows <T> {

    void gerarCabecalho(Document doc, String emailUsuario, String plate) throws DocumentException;

    int gerarCorpo(List<T> object, Document doc) throws DocumentException;

    void gerarRodape(Document doc, List<T> object, int totKm, String periodo) throws DocumentException;

    byte[] gerarRelatorio(List<T> object, String periodo, String emailUsuario);

//    int gerarCorpo(List<FlowModel> fluxos, Document doc) throws DocumentException;
}
