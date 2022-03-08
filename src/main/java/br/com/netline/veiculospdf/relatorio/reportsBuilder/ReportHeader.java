package br.com.netline.veiculospdf.relatorio.reportsBuilder;

import br.com.netline.veiculospdf.relatorio.model.Utils;
import com.itextpdf.text.*;

public class ReportHeader {
    static public void gerarCabecalho(Document doc, String emailUsuario) throws DocumentException {
        Paragraph p = new Paragraph();
        p.setAlignment(Element.ALIGN_LEFT);
        p.add(new Chunk(
                "Netline Telecom", new Font(Font.FontFamily.HELVETICA, 24, Element.TITLE, BaseColor.BLACK
        )));
        doc.add(p);

        p = new Paragraph();
        p.setAlignment(Element.ALIGN_LEFT);
        p.add(new Chunk(
                "Data: " + Utils.dateToString(),
                new Font(Font.FontFamily.HELVETICA, 11, Element.TITLE, BaseColor.DARK_GRAY
                )));
        doc.add(p);

        p = new Paragraph();
        p.setAlignment(Element.ALIGN_LEFT);
        p.add(new Chunk(
                "Identificador do usuário: " + emailUsuario,
                new Font(Font.FontFamily.HELVETICA, 11, Element.TITLE, BaseColor.DARK_GRAY
                )));
        doc.add(p);

        p = new Paragraph(" ");
        doc.add(p);
        p = new Paragraph(" ");
        doc.add(p);
    }
}
