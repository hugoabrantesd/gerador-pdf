package br.com.netline.veiculospdf.relatorio.controllers;

import br.com.netline.veiculospdf.relatorio.builder.RelatorioBuilder;
import br.com.netline.veiculospdf.relatorio.model.FluxoModel;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
public class RelatorioController {

    @PostMapping(value = "/gerar-pdf", produces = "application/text")
    public ResponseEntity<byte[]> gerarPdf(@RequestBody HashMap<String, Object> flows) {
        RelatorioBuilder relatorioBuilder = new RelatorioBuilder();
        try {
            String periodo = String.valueOf(flows.get("periodo"));
            String emailUsuario = String.valueOf(flows.get("email"));

            System.out.println("Requisição recebida de: " + emailUsuario);

            List<LinkedTreeMap<String, Object>> js = new Gson().fromJson(flows.get("json").toString(), List.class);

            List<FluxoModel> fluxosList = new ArrayList<>();
            for (LinkedTreeMap<String, Object> o : js) {
                //System.out.println(o.get("kmSaida"));
                fluxosList.add(FluxoModel.fromMap(o));
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

}
