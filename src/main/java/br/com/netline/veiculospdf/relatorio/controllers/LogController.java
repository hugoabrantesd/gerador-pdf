package br.com.netline.veiculospdf.relatorio.controllers;

import br.com.netline.veiculospdf.relatorio.model.LogModel;
import br.com.netline.veiculospdf.relatorio.repository.LogRepository;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@RestController
public class LogController {
    @CrossOrigin
    @PostMapping(value = "/registerLog", produces = "application/text", consumes = "application/json")
    public String registerLog(@RequestBody String logs, HttpServletRequest request) {
        LogModel logReceived = new Gson().fromJson(logs, LogModel.class);
        final LogRepository logRepository = new LogRepository();
        if (logReceived != null) {
            logReceived.setData(String.valueOf(new SimpleDateFormat(
                    "dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().getTime())));
            logRepository.registerLog(logReceived);
            return "200";
        }
        return "401";
    }
}
