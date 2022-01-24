package br.com.netline.veiculospdf.relatorio.builder;

import br.com.netline.veiculospdf.relatorio.model.FluxoModel;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

import java.util.List;

public interface Relatorio {

    void gerarCabecalho(Document doc, String emailUsuario) throws DocumentException;

    int gerarCorpo(List<FluxoModel> fluxos, Document doc) throws DocumentException;

    void gerarRodape(Document doc, List<FluxoModel> fluxos, int totKm, String periodo) throws DocumentException;

    byte[] gerarRelatorio(List<FluxoModel> fluxos, String periodo, String emailUsuario);

}
