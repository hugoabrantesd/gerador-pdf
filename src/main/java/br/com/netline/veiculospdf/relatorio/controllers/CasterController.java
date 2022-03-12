package br.com.netline.veiculospdf.relatorio.controllers;

import br.com.netline.veiculospdf.relatorio.model.CasterModel;
import br.com.netline.veiculospdf.relatorio.repository.CasterRepository;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class CasterController {
    @CrossOrigin
    @PostMapping(value = "/registerCaster", produces = "application/text", consumes = "application/json")
    public String registerCaster(@RequestBody String jsonCaster, HttpServletRequest request) {
        CasterModel casterModel = new Gson().fromJson(jsonCaster, CasterModel.class);
        if (casterModel != null) {
            CasterRepository casterRepository = new CasterRepository();
            casterRepository.saveCaster(casterModel);
            return "200";
        }
        return "401";
    }

    @CrossOrigin
    @PostMapping(value = "/recuperaUltimoRodizio", produces = "application/text", consumes = "application/json")
    public String getAllCasters(@RequestBody String jsonCaster, HttpServletRequest request) {
        CasterModel casterModel = new Gson().fromJson(jsonCaster, CasterModel.class);
        if (casterModel != null) {
            CasterRepository casterRepository = new CasterRepository();
            CasterModel caster = casterRepository.getLastCaster(casterModel.getVehiclePlate());
            return new Gson().toJson(caster);
        }
        return "401";
    }

}
