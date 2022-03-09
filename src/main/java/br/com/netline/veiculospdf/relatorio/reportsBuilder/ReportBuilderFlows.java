package br.com.netline.veiculospdf.relatorio.reportsBuilder;

import br.com.netline.veiculospdf.relatorio.interfaces.ReportFlows;
import br.com.netline.veiculospdf.relatorio.model.FlowModel;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.*;
import java.nio.file.Files;
import java.util.List;

public class ReportBuilderFlows implements ReportFlows<FlowModel> {

    private Paragraph paragraphTitleColumn(String title, int fontSize, BaseColor fontColor) {
        Paragraph p = new Paragraph();
        p.add(new Chunk(title,
                new Font(Font.FontFamily.HELVETICA, fontSize, Element.ALIGN_CENTER, fontColor)));
        return p;
    }

    private Paragraph customParagraph(String title, float fontSize, int alignment, BaseColor fontColor) {
        Paragraph p = new Paragraph();
        p.add(new Chunk(title,
                new Font(Font.FontFamily.HELVETICA, fontSize, alignment, fontColor)));
        return p;
    }

    @Override
    public void gerarCabecalho(Document doc, String emailUsuario) throws DocumentException {
        GenericItems.gerarCabecalho(doc, emailUsuario);
//        Paragraph p = new Paragraph();
//        p.setAlignment(Element.ALIGN_LEFT);
//        p.add(new Chunk(
//                "Netline Telecom", new Font(Font.FontFamily.HELVETICA, 24, Element.TITLE, BaseColor.BLACK
//        )));
//        doc.add(p);
//
//        p = new Paragraph();
//        p.setAlignment(Element.ALIGN_LEFT);
//        p.add(new Chunk(
//                "Data: " + Utils.dateToString(),
//                new Font(Font.FontFamily.HELVETICA, 11, Element.TITLE, BaseColor.DARK_GRAY
//                )));
//        doc.add(p);
//
//        p = new Paragraph();
//        p.setAlignment(Element.ALIGN_LEFT);
//        p.add(new Chunk(
//                "Identificador do usuário: " + emailUsuario,
//                new Font(Font.FontFamily.HELVETICA, 11, Element.TITLE, BaseColor.DARK_GRAY
//                )));
//        doc.add(p);
//
//        p = new Paragraph(" ");
//        doc.add(p);
//        p = new Paragraph(" ");
//        doc.add(p);
    }

    @Override
    public int gerarCorpo(List<FlowModel> fluxos, Document doc) throws DocumentException {
        PdfPTable table = new PdfPTable(7);
        table.setRunDirection(0);
        table.setWidthPercentage(100);

        Paragraph p1 = paragraphTitleColumn("Nome do técnico", 13, BaseColor.BLACK);
        Paragraph p2 = paragraphTitleColumn("Data / Hora / KM saída", 13, BaseColor.BLACK);
        Paragraph p3 = paragraphTitleColumn("Data / Hora / KM chegada", 13, BaseColor.BLACK);
        Paragraph p4 = paragraphTitleColumn("Total KM", 13, BaseColor.BLACK);
        Paragraph p5 = paragraphTitleColumn("Tempo decorrido", 13, BaseColor.BLACK);
        Paragraph p6 = paragraphTitleColumn("Setor", 13, BaseColor.BLACK);
        Paragraph p7 = paragraphTitleColumn("Status do veículo", 13, BaseColor.BLACK);

        PdfPCell cell1 = new PdfPCell(p1);
        PdfPCell cell2 = new PdfPCell(p2);
        PdfPCell cell3 = new PdfPCell(p3);
        PdfPCell cell4 = new PdfPCell(p4);
        PdfPCell cell5 = new PdfPCell(p5);
        PdfPCell cell6 = new PdfPCell(p6);
        PdfPCell cell7 = new PdfPCell(p7);

        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        table.addCell(cell4);
        table.addCell(cell5);
        table.addCell(cell6);
        table.addCell(cell7);

        int totKm = 0;

        for (FlowModel f : fluxos) {
            //int totKmParcial = 0;
            p1 = paragraphTitleColumn(f.getNomeTecnico(), 11, BaseColor.DARK_GRAY);
            p2 = customParagraph(f.getDataSaida() + " - "
                            + f.getHoraSaida() + " - " + f.getKmSaida(),
                    11, Element.ALIGN_CENTER, BaseColor.RED
            );
            p3 = customParagraph(f.getDataChegada() + " - "
                            + f.getHoraChegada() + " - " + f.getKmChegada(),
                    11, Element.ALIGN_CENTER, BaseColor.BLUE

            );
            p4 = paragraphTitleColumn(f.getTotalKm() + " ", 11, BaseColor.DARK_GRAY);
            p5 = paragraphTitleColumn(f.getTotalHoras(), 11, BaseColor.DARK_GRAY);
            p6 = paragraphTitleColumn(f.getSetorVeiculo(), 11, BaseColor.DARK_GRAY);
            p7 = paragraphTitleColumn(f.getStatusVeiculo(), 11, BaseColor.DARK_GRAY);

            cell1 = new PdfPCell(p1);
            cell2 = new PdfPCell(p2);
            cell3 = new PdfPCell(p3);
            cell4 = new PdfPCell(p4);
            cell5 = new PdfPCell(p5);
            cell6 = new PdfPCell(p6);
            cell7 = new PdfPCell(p7);

            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            table.addCell(cell5);
            table.addCell(cell6);
            table.addCell(cell7);
            totKm += f.getTotalKm();
        }
        doc.add(table);
        return totKm;
    }

    @Override
    public void gerarRodape(Document doc, List<FlowModel> fluxos, int totKm, String periodo) throws DocumentException {
        Paragraph p = new Paragraph();

        p.setAlignment(Element.ALIGN_RIGHT);
        p.add(new Chunk(
                " ",
                new Font(Font.FontFamily.HELVETICA, 10, Element.TITLE, BaseColor.BLACK
                )));
        doc.add(p);

        p = new Paragraph();
        p.setAlignment(Element.ALIGN_RIGHT);
        p.add(new Chunk(
                "------------------------------------------------------------------",
                new Font(Font.FontFamily.HELVETICA, 10, Element.TITLE, BaseColor.BLACK
                )));
        doc.add(p);

        p = new Paragraph();
        p.setAlignment(Element.ALIGN_RIGHT);
        p.add(new Chunk(
                "Total KM percorrido: " + totKm,
                new Font(Font.FontFamily.HELVETICA, 12, Element.TITLE, BaseColor.BLACK
                )));
        doc.add(p);

        p = new Paragraph();
        p.setAlignment(Element.ALIGN_RIGHT);
        p.add(new Chunk(
                "Total registros: " + fluxos.size(),
                new Font(Font.FontFamily.HELVETICA, 12, Element.TITLE, BaseColor.BLACK
                )));
        doc.add(p);

        p = new Paragraph();
        p.setAlignment(Element.ALIGN_RIGHT);
        p.add(new Chunk(
                "Período deste relatório: " + periodo,
                new Font(Font.FontFamily.HELVETICA, 12, Element.TITLE, BaseColor.BLACK
                )));
        doc.add(p);

        p = new Paragraph();
        p.setAlignment(Element.ALIGN_RIGHT);
        p.add(new Chunk(
                "------------------------------------------------------------------",
                new Font(Font.FontFamily.HELVETICA, 10, Element.TITLE, BaseColor.BLACK
                )));
        doc.add(p);
    }

    @Override
    public byte[] gerarRelatorio(List<FlowModel> fluxos, String periodo, String emailUsuario) {
        Document doc = new Document();
        doc.setPageSize(PageSize.A4.rotate());
        String arquivoPdf = "Relatório.pdf";

//        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();

        try {

            PdfWriter.getInstance(doc, new FileOutputStream(arquivoPdf));
            doc.open();

            gerarCabecalho(doc, emailUsuario);
            int totKm = gerarCorpo(fluxos, doc);
            gerarRodape(doc, fluxos, totKm, periodo);

            doc.close();

            File file = new File(arquivoPdf);
            return Files.readAllBytes(file.toPath());
//            return byteArray.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
