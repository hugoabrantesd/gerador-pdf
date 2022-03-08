package br.com.netline.veiculospdf.relatorio.controllers;

import br.com.netline.veiculospdf.relatorio.model.CasterModel;
import br.com.netline.veiculospdf.relatorio.reportsBuilder.ReportBuilderCaster;
import br.com.netline.veiculospdf.relatorio.reportsBuilder.ReportBuilderFlows;
import br.com.netline.veiculospdf.relatorio.model.FlowModel;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
public class ReportController {

    @CrossOrigin
    @GetMapping(value = "/")
    public String home() {
        return "Servidor ON!";
    }

    @CrossOrigin
    @PostMapping(value = "/gerar-pdf", produces = "application/text", consumes = "application/json")
    public ResponseEntity<byte[]> gerarPdf(@RequestBody HashMap<String, Object> flows, HttpServletRequest request) {
        ReportBuilderFlows relatorioBuilder = new ReportBuilderFlows();
        //System.out.println(request.getRequestURL().toString());
        try {
            String periodo = String.valueOf(flows.get("periodo"));
            String emailUsuario = String.valueOf(flows.get("email"));

            System.out.println("Requisição recebida de: " + emailUsuario);

            List<LinkedTreeMap<String, Object>> js = new Gson().fromJson(flows.get("json").toString(), List.class);

            List<FlowModel> fluxosList = new ArrayList<>();
            for (LinkedTreeMap<String, Object> o : js) {
                //System.out.println(o.get("kmSaida"));
                fluxosList.add(FlowModel.fromMap(o));
            }

            byte[] f = relatorioBuilder.gerarRelatorio(fluxosList, periodo, emailUsuario);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            // Here you have to set the actual filename of your pdf
            String filename = "output.pdf";
            headers.setContentDispositionFormData(filename, filename);
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

            return new ResponseEntity<>(f, headers, HttpStatus.OK);
//            return new ResponseEntity<>(new byte[0], new HttpHeaders(), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @CrossOrigin
    @PostMapping(value = "/gerarRelatorioRodizio", produces = "application/text", consumes = "application/text")
    public String generateReportCaster(@RequestBody String vehiclePlate, HttpServletRequest servletRequest){
        //String plate = jsonPlate;
        new ReportBuilderCaster().buildReport(vehiclePlate);
        return "teste".toUpperCase();
    }

}
