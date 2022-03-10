package br.com.netline.veiculospdf.relatorio.reportsBuilder;

import br.com.netline.veiculospdf.relatorio.model.Utils;
import com.itextpdf.text.*;

public class GenericItems {
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
                "Relatório gerado por: " + emailUsuario,
                new Font(Font.FontFamily.HELVETICA, 11, Element.TITLE, BaseColor.DARK_GRAY
                )));
        doc.add(p);

        p = new Paragraph(" ");
        doc.add(p);
        p = new Paragraph(" ");
        doc.add(p);
    }

    static public Paragraph paragraphTitleColumn(String title, int fontSize, BaseColor fontColor) {
        Paragraph p = new Paragraph();
        p.add(new Chunk(title,
                new Font(Font.FontFamily.HELVETICA, fontSize, Element.ALIGN_CENTER, fontColor)));
        return p;
    }

    static public Paragraph customParagraph(String title, float fontSize, int alignment, BaseColor fontColor) {
        Paragraph p = new Paragraph();
        p.add(new Chunk(title,
                new Font(Font.FontFamily.HELVETICA, fontSize, alignment, fontColor)));
        return p;
    }

}
