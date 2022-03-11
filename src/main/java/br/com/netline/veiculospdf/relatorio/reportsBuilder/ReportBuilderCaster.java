package br.com.netline.veiculospdf.relatorio.reportsBuilder;

import br.com.netline.veiculospdf.relatorio.interfaces.ReportFlows;
import br.com.netline.veiculospdf.relatorio.model.CasterModel;
import br.com.netline.veiculospdf.relatorio.repository.CasterRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.List;

public class ReportBuilderCaster implements ReportFlows<CasterModel> {

    private final CasterRepository casterRepository = new CasterRepository();

    public byte[] buildReport(String emailUser, String plate) {
        List<CasterModel> casters = casterRepository.getAllCasters(plate);
        if (casters.isEmpty()) {
            return new byte[0];
        }
//        System.out.println(casters.size());
//        casters.forEach((element) -> System.out.println(element.getVehiclePlate()));

        //TODO: inserir novos dados no rodapé do relatório.

        return this.gerarRelatorio(casters, "", emailUser);
    }


    @Override
    public void gerarCabecalho(Document doc, String emailUsuario, String plate) throws DocumentException {
        GenericItems.gerarCabecalho(doc, emailUsuario);
        Paragraph p = new Paragraph();
        p.setAlignment(Element.ALIGN_LEFT);
        p.add(new Chunk(
                "Relatório dos rodízios de pneus do veículo: " + plate, new Font(Font.FontFamily.HELVETICA, 13, Element.TITLE, BaseColor.BLACK
        )));
        doc.add(p);
        p = new Paragraph(" ");
        doc.add(p);
        p = new Paragraph(" ");
        doc.add(p);
    }

    @Override
    public int gerarCorpo(List<CasterModel> casters, Document doc) throws DocumentException {
        PdfPTable table = new PdfPTable(9);
        table.setRunDirection(0);
        table.setWidthPercentage(100);

        float[] columnWidths = new float[]{
                30f, 20f, 20f, 25, 20f, 30f, 27f, 27f, 50f
        };
        table.setWidths(columnWidths);

        Paragraph p1 = GenericItems.paragraphTitleColumn("Usuário", 11, BaseColor.BLACK);
        Paragraph p2 = GenericItems.paragraphTitleColumn("Data", 11, BaseColor.BLACK);
        Paragraph p3 = GenericItems.paragraphTitleColumn("KM rodízio", 11, BaseColor.BLACK);
        Paragraph p4 = GenericItems.paragraphTitleColumn("Próx. rodízio", 11, BaseColor.BLACK);
        Paragraph p5 = GenericItems.paragraphTitleColumn("Placa", 11, BaseColor.BLACK);

        p5.setIndentationRight(0);

        Paragraph p6 = GenericItems.paragraphTitleColumn("Tipo rodizio", 11, BaseColor.BLACK);
        Paragraph p7 = GenericItems.paragraphTitleColumn("Diant. trocados", 11, BaseColor.BLACK);
        Paragraph p8 = GenericItems.paragraphTitleColumn("Tras. trocados", 11, BaseColor.BLACK);
        Paragraph p9 = GenericItems.paragraphTitleColumn("OBS", 11, BaseColor.BLACK);
        p9.setAlignment(Element.ALIGN_CENTER);

        PdfPCell cell1 = new PdfPCell(p1);
        PdfPCell cell2 = new PdfPCell(p2);
        PdfPCell cell3 = new PdfPCell(p3);
        PdfPCell cell4 = new PdfPCell(p4);
        PdfPCell cell5 = new PdfPCell(p5);
        cell5.setPaddingRight(0);

        PdfPCell cell6 = new PdfPCell(p6);
        PdfPCell cell7 = new PdfPCell(p7);
        PdfPCell cell8 = new PdfPCell(p8);
        PdfPCell cell9 = new PdfPCell(p9);
        cell9.setHorizontalAlignment(Element.ALIGN_CENTER);

        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        table.addCell(cell4);
        table.addCell(cell5);

        table.addCell(cell6);
        table.addCell(cell7);
        table.addCell(cell8);
        table.addCell(cell9);

        for (CasterModel c : casters) {
            //int totKmParcial = 0;
            p1 = GenericItems.paragraphTitleColumn(c.getEmailUser(), 10, BaseColor.DARK_GRAY);
            p2 = GenericItems.customParagraph(c.getDateLastCast(), 10, Element.ALIGN_CENTER, BaseColor.RED);
            p3 = GenericItems.customParagraph(c.getKmLastCaster().toString(),
                    10, Element.ALIGN_CENTER, BaseColor.RED);
            p4 = GenericItems.paragraphTitleColumn(c.getKmNextCaster().toString(), 10, BaseColor.BLUE);
            p5 = GenericItems.paragraphTitleColumn(c.getVehiclePlate(), 10, BaseColor.DARK_GRAY);
            p5.setIndentationRight(0);

            final String front;
            final String rear;

            if (c.getExchangedFront()) {
                front = "Sim";
            } else {
                front = "Não";
            }

            if (c.getExchangedRear()) {
                rear = "Sim";
            } else {
                rear = "Não";
            }

            p6 = GenericItems.paragraphTitleColumn(c.getType(), 10, BaseColor.DARK_GRAY);
            p7 = GenericItems.paragraphTitleColumn(front, 10, BaseColor.DARK_GRAY);
            p8 = GenericItems.paragraphTitleColumn(rear, 10, BaseColor.DARK_GRAY);
            p9 = GenericItems.paragraphTitleColumn(c.getObservation(), 10, BaseColor.DARK_GRAY);
//            p9.setAlignment(Element.ALIGN_JUSTIFIED_ALL);

            cell1 = new PdfPCell(p1);
            cell1.setFixedHeight(30);
            cell2 = new PdfPCell(p2);
            cell3 = new PdfPCell(p3);
            cell4 = new PdfPCell(p4);
            cell5 = new PdfPCell(p5);
            cell5.setPaddingRight(0);
            cell6 = new PdfPCell(p6);
            cell7 = new PdfPCell(p7);
            cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell8 = new PdfPCell(p8);
            cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell9 = new PdfPCell(p9);
//            cell9.setHorizontalAlignment(Element.ALIGN_CENTER);

            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            table.addCell(cell5);
            table.addCell(cell6);
            table.addCell(cell7);
            table.addCell(cell8);
            table.addCell(cell9);
        }
        doc.add(table);
        return 0;
    }

    @Override
    public void gerarRodape(Document doc, List<CasterModel> caster, int totKm, String periodo) {
    }

    @Override
    public byte[] gerarRelatorio(List<CasterModel> casters, String periodo, String emailUsuario) {
        Document doc = new Document();
        doc.setPageSize(PageSize.A4.rotate());
        String arquivoPdf = "Relatório.pdf";

        try {

            PdfWriter.getInstance(doc, new FileOutputStream(arquivoPdf));
            doc.open();

            gerarCabecalho(doc, emailUsuario, casters.get(0).getVehiclePlate());
            int totKm = gerarCorpo(casters, doc);
            gerarRodape(doc, casters, totKm, periodo);

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
