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
        // TODO: gerar o relatório a partir dessa lista
        List<CasterModel> casters = casterRepository.getAllCasters(plate);
        if(casters.isEmpty()){
            return new byte[0];
        }
        return this.gerarRelatorio(casters, "", emailUser);
    }


    @Override
    public void gerarCabecalho(Document doc, String emailUsuario) throws DocumentException {
        GenericItems.gerarCabecalho(doc, emailUsuario);
    }

    @Override
    public int gerarCorpo(List<CasterModel> casters, Document doc) throws DocumentException {
        PdfPTable table = new PdfPTable(5);
        table.setRunDirection(0);
        table.setWidthPercentage(100);

        Paragraph p1 = GenericItems.paragraphTitleColumn("Identificador usuário", 12, BaseColor.BLACK);
        Paragraph p2 = GenericItems.paragraphTitleColumn("Data último rodízio", 12, BaseColor.BLACK);
        Paragraph p3 = GenericItems.paragraphTitleColumn("KM último rodízio", 12, BaseColor.BLACK);
        Paragraph p4 = GenericItems.paragraphTitleColumn("KM próximo rodízio", 12, BaseColor.BLACK);
        Paragraph p5 = GenericItems.paragraphTitleColumn("Placa", 12, BaseColor.BLACK);

        PdfPCell cell1 = new PdfPCell(p1);
        PdfPCell cell2 = new PdfPCell(p2);
        PdfPCell cell3 = new PdfPCell(p3);
        PdfPCell cell4 = new PdfPCell(p4);
        PdfPCell cell5 = new PdfPCell(p5);

        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        table.addCell(cell4);
        table.addCell(cell5);

        for (CasterModel c : casters) {
            //int totKmParcial = 0;
            p1 = GenericItems.paragraphTitleColumn(c.getEmailUser(), 11, BaseColor.DARK_GRAY);
            p2 = GenericItems.customParagraph(c.getDateLastCast(), 11, Element.ALIGN_CENTER, BaseColor.RED);
            p3 = GenericItems.customParagraph(c.getKmLastCaster().toString(),
                    11, Element.ALIGN_CENTER, BaseColor.RED);
            p4 = GenericItems.paragraphTitleColumn(c.getKmNextCaster().toString(), 11, BaseColor.BLUE);
            p5 = GenericItems.paragraphTitleColumn(c.getVehiclePlate(), 11, BaseColor.DARK_GRAY);

            cell1 = new PdfPCell(p1);

            cell1.setFixedHeight(25);

            cell2 = new PdfPCell(p2);
            cell3 = new PdfPCell(p3);
            cell4 = new PdfPCell(p4);
            cell5 = new PdfPCell(p5);

            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            table.addCell(cell5);
        }
        doc.add(table);
        return 0;
    }

    @Override
    public void gerarRodape(Document doc, List<CasterModel> caster, int totKm, String periodo) {}

    @Override
    public byte[] gerarRelatorio(List<CasterModel> casters, String periodo, String emailUsuario) {
        Document doc = new Document();
        doc.setPageSize(PageSize.A4.rotate());
        String arquivoPdf = "Relatório.pdf";

        try {

            PdfWriter.getInstance(doc, new FileOutputStream(arquivoPdf));
            doc.open();

            gerarCabecalho(doc, emailUsuario);
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
