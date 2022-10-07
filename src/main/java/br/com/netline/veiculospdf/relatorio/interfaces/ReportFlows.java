package br.com.netline.veiculospdf.relatorio.interfaces;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

import java.util.List;

public interface ReportFlows <T> {

    void generateHeader(Document doc, String emailUsuario, String plate) throws DocumentException;

    int generateBody(List<T> object, Document doc) throws DocumentException;

    void generateBaseboard(Document doc, List<T> object, int totKm, String periodo) throws DocumentException;

    byte[] generateReport(List<T> object, String periodo, String emailUsuario);

//    int gerarCorpo(List<FlowModel> fluxos, Document doc) throws DocumentException;
}
