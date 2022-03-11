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
            String filename = "output.pdf";
            headers.setContentDispositionFormData(filename, filename);
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

            return new ResponseEntity<>(f, headers, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new byte[0], new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin
    @PostMapping(value = "/gerarRelatorioRodizio", produces = "application/text", consumes = "application/json")
    public ResponseEntity<byte[]> generateReportCaster(@RequestBody String json, HttpServletRequest servletRequest) {
        //String plate = jsonPlate;
        try {
            HashMap<String, String> body = new Gson().fromJson(json, HashMap.class);
            String emailUser = body.get("email");
            String plate = body.get("vehiclePlate");

            System.out.println("email: " + emailUser);
            System.out.println("placa: " + plate);

            byte[] f = new ReportBuilderCaster().buildReport(emailUser, plate);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            String filename = "Relatorio_rodizios.pdf";
            headers.setContentDispositionFormData(filename, filename);
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
            return new ResponseEntity<>(f, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new byte[0], new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
    }

}
